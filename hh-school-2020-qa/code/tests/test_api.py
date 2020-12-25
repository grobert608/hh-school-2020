import pytest


class TestApi:
    @pytest.mark.API
    def test_correct_token_auth(self, api_client):
        request = api_client.check_auth()
        assert request.status_code == 200
        assert request.json()['auth_type'] == 'application'
        assert request.json()['is_application'] == True

    @pytest.mark.API
    @pytest.mark.parametrize("text_request",
                             ['JAVA', 'scala', 'Разработка', 'тестирование', 'QA-инженер'])
    def test_correct_simple_vacancies_search(self, api_client, text_request):
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0

    @pytest.mark.API
    @pytest.mark.parametrize("text_request",
                             ['', ' ', 'Р', 'т', 'J', 'QA'])
    def test_min_len_of_vacancies_search(self, api_client, text_request):
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0

    @pytest.mark.API
    @pytest.mark.parametrize("text_request",
                             ['T' * 1000, 'R' * 2000, 'I' * 3000, 'J' * 4000, 'Э' * 4095, 'A' * 4096, 'a' * 4096,
                              'я' * 4096, 'Ю' * 4096])
    def test_max_len_of_vacancies_search(self, api_client, text_request):
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200

    @pytest.mark.API
    @pytest.mark.parametrize("text_request",
                             ['!JAVA', '!QA', '!Инженер'])
    def test_containing_of_sought_vacancies(self, api_client, text_request):
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_all_name_of_vacancies_in_request(request, 'name', text_request[2:]) == True

    @pytest.mark.API
    @pytest.mark.parametrize("text_request",
                             ['"Продажа оборудования"', "JAVA разработчик"])
    def test_containing_of_sought_vacancies_with_quotes(self, api_client, text_request):
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_all_name_of_vacancies_in_request(request, 'name',
                                                                 text_request[2:len(text_request) - 1]) == True

    @pytest.mark.API
    def test_containing_of_sought_vacancies_with_quotes(self, api_client):
        text_request = 'Гео*'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'геолог') is not None
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'геодезист') is not None

    @pytest.mark.API
    def test_containing_of_sought_vacancies_with_quotes(self, api_client):
        text_request = 'Гео*'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'геолог') is not None
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'геодезист') is not None

    @pytest.mark.API
    def test_containing_of_sought_vacancies_with_synonyms(self, api_client):
        text_request = 'pr-менеджер'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'pr-менеджер') is not None
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name',
                                                                 'менеджер по связям с общественностью') is not None

    @pytest.mark.API
    def test_containing_of_sought_vacancies_with_or_search(self, api_client):
        text_request = '!нефть OR !бензин'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'нефть') is not None
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'бензин') is not None

    @pytest.mark.API
    def test_containing_of_sought_vacancies_with_not_search(self, api_client):
        text_request = 'NOT !нефть'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'нефть') is None

    @pytest.mark.API
    def test_containing_of_sought_vacancies_by_fields(self, api_client):
        text_request = 'NAME:(python OR java) and !ID:39796769'
        request = api_client.search_vacancies(text_request)
        assert request.status_code == 200
        assert request.json()['found'] > 0
        assert api_client.check_one_name_of_vacancies_in_request(request, 'name', 'Java') is not None \
               or api_client.check_one_name_of_vacancies_in_request(request, 'name', 'Python') is not None
        assert api_client.check_one_name_of_vacancies_in_request(request, 'id', '39796769') is not None
