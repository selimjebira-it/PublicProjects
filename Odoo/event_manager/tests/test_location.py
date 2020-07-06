# -*- coding: utf-8 -*-
from odoo.tests import common
from odoo.exceptions import ValidationError


class TestLocation(common.TransactionCase):

    def init_location(self):
        name = "Une Super Adresse"
        town = "Une Super ville"
        street = "Une Super rue"
        number = 7
        return self.env['eventmanager.location'].create({
            'name': name,
            'town': town,
            'street': street,
            'number': number, })

    def test_location_ok(self):
        location = self.init_location()

    def test_location_notOk(self):
        with self.assertRaises(ValidationError):
            name = "Une Super Adresse"
            town = "Une Super ville"
            street = "Une Super rue"
            number = -2
            self.env['eventmanager.location'].create({
                'name': name,
                'town': town,
                'street': street,
                'number': number
            })
