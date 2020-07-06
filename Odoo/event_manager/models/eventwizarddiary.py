# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from datetime import datetime


class EventWizardDiary(models.TransientModel):

    _name = "eventmanager.event_wizard_diary"

    def _default_event(self):
        return self.env['eventmanager.event'].browse(self._context.get('active_id'))

    event_ids = fields.Many2many('eventmanager.event',
                                 string="Event", required=True, default=_default_event)



    def subscribeDiary(self):
        for event in self.event_ids:
            for suscriber in event.diary_id.member_ids:
                event.attendees_ids|= suscriber
            
