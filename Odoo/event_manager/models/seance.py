# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from . import course
from datetime import datetime

# extension of an event related to a seance


class Seance(models.Model):

    _name = 'eventmanager.seance'
    _description = 'seance of a course'
    _inherit = 'eventmanager.event'

    # override Description
    description = fields.Selection(course.COURSE_TYPES,
                                   required=True,
                                   string='Type'
                                   )

    # instructor for the seance
    instructor = fields.Many2one(
        'res.users', string='Instructor', required=True)

    # course related to the seance
    course_id = fields.Many2one('eventmanager.course', string='Course',
                                ondelete='cascade'
                                )

    # override name
    name = fields.Char(
        compute='_compute_name', string="Slug"
    )

    @api.model
    def init_seance(self):
        teacher = self.env["res.users"].create(
            {"name": "sélim Prof", "login": "Qualité"})
        teacher.write(
            {'groups_id': [(4, self.env.ref('event_manager.group_teacher').id)], })
        for i in range(5):
            course_id = self.env.ref("event_manager.cours%d" % i)
            location_id = self.env.ref("event_manager.location%d" % i).id
            diary_id = self.env.ref("event_manager.diary%d" %i).id
            start_date = datetime(2022, 1, 15 + i, 6, 0, 0, 0, None)
            end_date = datetime(2022, 1, 15 + i, 8, 0, 0, 0, None)
            description = course.COURSE_TYPES[0][0]
            values = {
                "course_id": course_id.id,
                "location_id":  location_id,
                "diary_id":diary_id,
                "name": course_id.slug,
                "start_date": start_date,
                "end_date": end_date,
                "description": description,
                "instructor": teacher.id
            }
            super(Seance, self).create(values)

    @api.one
    @api.constrains('instructor')
    def _check_instructor(self):
        isTeacher = self.instructor.has_group('event_manager.group_teacher')
        if(not isTeacher):
            raise ValidationError("this user is not a teacher")

    @api.one
    @api.depends('course_id')
    def _compute_name(self):
        self.name = self.course_id.slug
        return self.course_id.slug


# Course Extension for seance ids


class CourseSeance(models.Model):

    _inherit = "eventmanager.course"

    seance_ids = fields.One2many(
        'eventmanager.seance', 'course_id', string='Seances')
