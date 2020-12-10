from urllib.parse import urljoin

import requests

from api.urls.urls import Urls


class ApiClient:
    def __init__(self, base_url, token):
        self.base_url = base_url
        self.token = token
        self.session = requests.Session()
        self.headers = None

    def _request(self, method, location, headers=None, data=None, redirect=False):
        url = urljoin(self.base_url, location)
        return self.session.request(method, url, headers=headers, data=data, allow_redirects=redirect)

    def check_auth(self):
        headers = {"Authorization": "Bearer {}".format(self.token)}
        return self._request('GET', self.base_url + Urls.AUTH_CHECK, headers=headers)

    def search_vacancies(self, text):
        headers = {"Authorization": "Bearer {}".format(self.token)}
        return self._request('GET', self.base_url + Urls.SEARCH_VACANCIES(text), headers=headers)

    def check_one_name_of_vacancies_in_request(self, request, field, text):
        return next((item for item in request.json()['items'] if text.lower() in item[field].lower()), None)

    def check_all_name_of_vacancies_in_request(self, request, field, text):
        return all(item for item in request.json()['items'] if text.lower() in item[field].lower())
