from api.fixtures import *


def pytest_addoption(parser):
    parser.addoption('--url', default='https://api.hh.ru/')
    parser.addoption('--token', default='dummy_token')


@pytest.fixture(scope='session')
def config(request):
    url = request.config.getoption('--url')
    token = request.config.getoption('--token')

    return {'url': url, 'token': token}
