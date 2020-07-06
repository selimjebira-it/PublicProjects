# -*- coding: utf-8 -*-
from odoo import api, fields, models
from odoo.exceptions import ValidationError


COURSE_TYPES = [
    ('1', 'Course'), ('2', 'Laboratory'), ('3', 'Interrogation')
]

# Course Class 
class Course(models.Model):

    _name = "eventmanager.course"

    # slug of the course
    slug = fields.Char(size=5, require=True, string="ID")
    # name of the course
    name = fields.Char(size=30, required=True, string="Name")

    description = fields.Text(string='Description')
    # ects of the course
    ects = fields.Integer(size=2, required=True, string="ECTS")

    @api.one
    @api.constrains('ects')
    def constrains_ects(self):
        if(self.ects < 1 or self.ects >15):
            raise ValidationError("ects must be between 1 and 15")






