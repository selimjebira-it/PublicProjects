# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from . import event


DIARY_TYPE = [('1', 'Administratif'), ('2', 'Étudiant'), ('3', 'Pédagogique')]


# extension of an diary related to a seance
class Diary(models.Model):

    _name = 'eventmanager.diary'
    _description = 'user \'s calandars'

    # name for diary
    name = fields.Char(string='Name', required=True, size=20)

    # owner of the diary
    owner_id = fields.Many2one(
        'res.users', 'Current User', default=lambda self: self.env.user.id)

    # diary 's events list
    event_ids = fields.One2many('eventmanager.event', 'diary_id', string='Events')

    # diary 's users list
    member_ids = fields.Many2many('res.users', string='Members')

    # diary 's users list
    diary_type = fields.Selection(DIARY_TYPE, string='Type', required=True)

    is_organizer = fields.Boolean(string='estOrganisateur')

    def isGoodType(self):
        if(not(self.owner_id.has_group('event_manager.group_administrator'))):
            isTeacher = self.owner_id.has_group('event_manager.group_teacher')
            isStudent = self.owner_id.has_group('event_manager.group_student')
            isAdministrative = self.owner_id.has_group(
            'event_manager.group_administrative')

            if(isTeacher and self.diary_type == DIARY_TYPE[2][0]):
                return True

            if(isStudent and self.diary_type == DIARY_TYPE[1][0]):
                return True

            if(isAdministrative and self.diary_type == DIARY_TYPE[0][0]):
                return True
            return False

        else:
            return True

    @api.multi
    def write(self, values):
        if(not(self.isGoodType())):
            raise ValidationError("You cannot modify this")
        return super(Diary, self).write(values)

    @api.multi
    def delete(self):
        if(not(self.isGoodType())):
            raise ValidationError("You cannot delete this")
        return super(Diary, self).delete()

    @api.one
    @api.depends('is_organizer')
    def _compute_name(self):
        return self.owner_id == self.env.user.id

    @api.one
    @api.constrains('diary_type')
    def _check_type(self):
        isTeacher = self.owner_id.has_group('event_manager.group_teacher')
        isStudent = self.owner_id.has_group('event_manager.group_student')
        isAdministrative = self.owner_id.has_group(
            'event_manager.group_administrative')

        if(isTeacher and self.diary_type != DIARY_TYPE[2][0]):
            raise ValidationError(
                "you can\'t choice this type because you are in the teacher group")

        if(isStudent and self.diary_type != DIARY_TYPE[1][0]):
            raise ValidationError(
                "you can\'t choice this type because you are in the student group")

        if(isAdministrative and self.diary_type != DIARY_TYPE[0][0]):
            raise ValidationError(
                "you can\'t choice this type because you are in the administrativ group")

    

# Course Extension for seance ids


class UserDiary(models.Model):

    _inherit = "res.users"

    diary_ids = fields.One2many(
        'eventmanager.diary', 'owner_id', string='MyDiarys')
