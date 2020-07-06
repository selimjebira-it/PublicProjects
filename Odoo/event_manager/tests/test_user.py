# -*- coding: utf-8 -*-
from odoo.tests import common
from odoo.exceptions import ValidationError


class TestUser(common.TransactionCase):

    def test_student(self):
        user = self.env["res.users"].create({"name":"Selim Le Testeur","login":"DES TEST"})
        user.write({'groups_id': [(4, self.ref('event_manager.group_teacher'))], })
        


    def test_teacher(self):
        user = self.env["res.users"].create({"name":"Selim Le Testeur","login":"DES TEST"})
        user.write({'groups_id': [(4, self.ref('event_manager.group_student'))], })


    def test_admin(self):
        user = self.env["res.users"].create({"name":"Selim Le Testeur","login":"DES TEST"})
        user.write({'groups_id': [(4, self.ref('event_manager.group_administrative'))], })


    def test_adminstrator(self):
        user = self.env["res.users"].create({"name":"Selim Le Testeur","login":"DES TEST"})
        user.write({'groups_id': [(4, self.ref('event_manager.group_administrator'))], })

    
