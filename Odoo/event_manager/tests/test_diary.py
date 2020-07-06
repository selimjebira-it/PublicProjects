from odoo.tests import common
from odoo.exceptions import ValidationError
from ..models import course,diary


class TestDiary(common.TransactionCase):

    def test_ok(self):

        name = "Bonjour"

        owner_id = self.env['res.users'].create({'name':'Youri','login':'YouriLogin'}).id

        diary_type = diary.DIARY_TYPE[0][0]

        self.env['eventmanager.diary'].create({
            "name" : name,
            "owner_id":owner_id,
            "diary_type":diary_type
        })