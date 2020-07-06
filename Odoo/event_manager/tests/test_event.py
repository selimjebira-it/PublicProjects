# -*- coding: utf-8 -*-
from odoo.tests import common
from odoo.exceptions import ValidationError
from ..models import event,diary
import datetime


class TestEvent(common.TransactionCase):

    def _init_location(self):
        name = "Une Super Adresse"
        town = "Une Super ville"
        street = "Une Super rue"
        number = 7
        return self.env['eventmanager.location'].create({
            'name': name,
            'town': town,
            'street': street,
            'number': number,
        })

    def _init_diary(self):
        name = "Bonjour"

        owner_id = self.env['res.users'].create(
            {'name': 'Youri', 'login': 'YouriLogin'}).id

        diary_type = diary.DIARY_TYPE[0][0]

        return self.env['eventmanager.diary'].create({
            "name": name,
            "owner_id": owner_id,
            "diary_type": diary_type
        })

    def test_init_location_ok(self):
        location = self._init_location()
        self.assertEqual(location.number, 7)

    def test_creation_event(self):
        start_date = datetime.datetime(2022, 1, 15, 6, 0, 0, 0, None)
        end_date = datetime.datetime(2022, 1, 15, 8, 0, 0, 0, None)
        name = "EventOk"
        description = "Ma Super Description"
        location = self._init_location().id
        diary_id = self._init_diary().id
        eventOk = self.env["eventmanager.event"].create(
            {"name": name,"diary_id":diary_id, "description": description, "location_id": location, "start_date": start_date, "end_date": end_date})
        self.assertEqual(name, eventOk.name)

    def test_creation_badDate_event(self):
        start_date = datetime.datetime(2022, 1, 15, 6, 0, 0, 0, None)
        bad_end_date = datetime.datetime(2022, 1, 15, 4, 0, 0, 0, None)
        name = "EventNotOk"
        description = "Ma Super Description"
        diary_id = self._init_diary().id
        location = self._init_location().id
        with self.assertRaises(ValidationError):
            self.env["eventmanager.event"].create(
                {"name": name,"diary_id":diary_id, "description": description, "location_id": location, "start_date": start_date, "end_date": bad_end_date})

    def test_creation_badDate_event_past(self):
        start_date = datetime.datetime(1969, 1, 15, 6, 0, 0, 0, None)
        bad_end_date = datetime.datetime(1969, 1, 15, 4, 0, 0, 0, None)
        name = "EventNotOk"
        description = "Ma Super Description"
        location = self._init_location().id
        diary_id = self._init_diary().id
        with self.assertRaises(ValidationError):
            self.env["eventmanager.event"].create(
                {"name": name,"diary_id":diary_id, "description": description, "location_id": location, "start_date": start_date, "end_date": bad_end_date})
