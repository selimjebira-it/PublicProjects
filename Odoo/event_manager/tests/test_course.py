# -*- coding: utf-8 -*-
from odoo.tests import common
from odoo.exceptions import ValidationError
from ..models import course


class TestCourse(common.TransactionCase):

    def test_course_creation_tooPositive_ects(self):
        with self.assertRaises(ValidationError):
            course = self.env['eventmanager.course'].create(
                {"slug": 'INF', "name": 'informatique', "ects": 18})
                
    def test_course_creation_null_ects(self):
        with self.assertRaises(ValidationError):
            course = self.env['eventmanager.course'].create(
                {"slug": 'INF', "name": 'informatique', "ects": 0})

    def test_course_creation_negative_ects(self):
        with self.assertRaises(ValidationError):
            course = self.env['eventmanager.course'].create(
                {"slug": 'INF', "name": 'informatique', "ects": -5})

    def test_course_creation_ok(self):
        course = self.env['eventmanager.course'].create(
                {"slug": 'INF', "name": 'informatique', "ects": 10})
            
