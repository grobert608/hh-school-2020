class Urls:
    AUTH_CHECK = "me"

    @staticmethod
    def SEARCH_VACANCIES(text_field):
        return 'vacancies?text={}'.format(text_field)
