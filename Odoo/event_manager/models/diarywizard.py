# -*- coding: utf-8 -*-

from odoo import models, fields, api


class DiaryWizard(models.TransientModel):

    _name = "eventmanager.diary_wizard"
    _description = "Wizard: Quick Registration of users to diary"

    def _default_diary(self):
        return self.env['eventmanager.diary'].browse(self._context.get('active_id'))

    def _default_user(self):
        return self.env['res.users'].browse(self.env.user.id)
        

    diary_ids = fields.Many2many('eventmanager.diary',
                                 string="Diary", required=True, default=_default_diary)

    subscriber_ids = fields.Many2many(
        'res.users', string="Subscribers", required=True, default=_default_user )

    @api.multi
    def subscribe(self):
        for diary in self.diary_ids:
            diary.member_ids |= self.subscriber_ids
        return {}
