# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from datetime import datetime


class EventWizardSubscribe(models.TransientModel):

    _name = "eventmanager.event_wizard_subscribe"

    def _default_event(self):
        return self.env['eventmanager.event'].browse(self._context.get('active_id'))

    def _default_user(self):
        return self.env['res.users'].browse(self.env.user.id)

    event_ids = fields.Many2many('eventmanager.event',
                                 string="Event", required=True, default=_default_event)

    subscriber_ids = fields.Many2many(
        'res.users', string="Subscribers", required=True, default=_default_user)


    @api.multi
    def subscribe(self):
        for event in self.event_ids:
            event._constrains_max_attendees()
            event.attendees_ids |= self.subscriber_ids
        return {}
