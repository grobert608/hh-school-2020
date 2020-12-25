import pytest

from api.apiclient import ApiClient


@pytest.fixture(scope='function')
def api_client(config):
    return ApiClient(config['url'], config['token'])

