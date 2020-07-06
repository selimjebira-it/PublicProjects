# -*- coding: utf-8 -*-
from odoo.tests import common
from odoo.exceptions import ValidationError
from ..models import seance, course,diary
from .test_location import TestLocation
import datetime


class Testseance(common.TransactionCase):

    def _init_course(self):
        return self.env['eventmanager.course'].create(
            {"slug": 'INF', "name": 'informatique', "ects": 10})

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

    def _init_event(self):
        start_date = datetime.datetime(2022, 1, 15, 6, 0, 0, 0, None)
        end_date = datetime.datetime(2022, 1, 15, 8, 0, 0, 0, None)
        name = "EventOk"
        description = "Ma Super Description"
        location = TestLocation.init_location(self).id
        diary_id = self._init_diary().id
        return self.env["eventmanager.event"].create(
            {"name": name,"diary_id":diary_id, "description": description, "location_id": location, "start_date": start_date, "end_date": end_date})

    def _init_teacher(self):
        user = self.env["res.users"].create(
            {"name": "Selim Le Testeur", "login": "DES TEST"})
        user.write(
            {'groups_id': [(4, self.ref('event_manager.group_teacher'))], })
        return user

    def _init_student(self):
        user = self.env["res.users"].create(
            {"name": "Selim Le Testeur", "login": "DES TEST"})
        user.write(
            {'groups_id': [(4, self.ref('event_manager.group_student'))], })
        return user

    def test_course_init_ok(self):
        course = self._init_course()
        self.assertEqual(10, course.ects)

    def test_event_init_ok(self):
        event = self._init_event()
        name = "EventOk"
        self.assertEqual(name, event.name)

    def test_user_init_ok(self):
        user = self._init_teacher()
        self.assertTrue(user.has_group('event_manager.group_teacher'))

    def test_seance_ok(self):
        start_date = datetime.datetime(2022, 1, 15, 6, 0, 0, 0, None)
        end_date = datetime.datetime(2022, 1, 15, 8, 0, 0, 0, None)
        name = "EventOk"
        description = "Ma Super Description"
        location = TestLocation.init_location(self).id
        diary_id = self._init_diary().id
        instructor = self._init_teacher().id
        course = self._init_course().id
        self.env["eventmanager.event"].create(
            {"name": name,"diary_id":diary_id,"description": description, "location_id": location,
             "start_date": start_date, "end_date": end_date,
                "instructor": instructor, "course_id": course
             })

    def test_seance_not_ok(self):
        start_date = datetime.datetime(2022, 1, 15, 6, 0, 0, 0, None)
        end_date = datetime.datetime(2022, 1, 15, 8, 0, 0, 0, None)
        name = "EventOk"
        description = "Ma Super Description"
        location = TestLocation.init_location(self).id
        student = self._init_student().id
        diary_id = self._init_diary().id
        course = self._init_course().id
        self.env["eventmanager.event"].create(
            {"name": name,"diary_id":diary_id, "description": description, "location_id": location,
             "start_date": start_date, "end_date": end_date,
                "instructor": student, "course_id": course
             })
