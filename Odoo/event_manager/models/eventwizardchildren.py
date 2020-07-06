# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from datetime import datetime,timedelta
from dateutil import relativedelta
from .event import Event


PERIOD_TYPES = [
    ('1', 'Monthly'), ('2', 'Weekly'), ('3', 'Daily')
]


class EventWizardChildren(models.TransientModel):

    _name = "eventmanager.event_wizard_children"

    # parent
    parent_id = fields.Many2one('eventmanager.event', string='Parent', default=lambda self: self.env['eventmanager.event'].browse(
        self._context.get('active_id')), readonly=True)
    # children
    children_ids = fields.Many2many(
        'eventmanager.event', string='Children', readonly=True)

    # periodicity month,week,days
    period_type = fields.Selection(PERIOD_TYPES, string='Periodicity')
    # counting days,months,weeks
    period = fields.Integer(string='Duree', default=1)

    @api.constrains('period', 'period_type')
    def _check_period(self):

        if(self.period <= 0):
            raise ValidationError('duree must be positive')

        if(self.period > 12 and self.period_type == PERIOD_TYPES[0][0]):
            raise ValidationError('Maximum 12 months')

        if(self.period > 52 and self.period_type == PERIOD_TYPES[1][0]):
            raise ValidationError('maximum 52 weeks')

        if(self.period > 365 and self.period_type == PERIOD_TYPES[2][0]):
            raise ValidationError('Maximum 365 days')


    @api.multi
    @api.depends("period","period_type")
    def save(self):
        # Assign parent the parent (Head )
        name = self.parent_id.name
        start_date = datetime.strptime(self.parent_id.start_date, '%Y-%m-%d %H:%M:%S')
        end_date = datetime.strptime(self.parent_id.end_date, '%Y-%m-%d %H:%M:%S')

        timeD = timedelta(weeks=+4) if self.period_type == PERIOD_TYPES[0][0] else timedelta(
            days=+7) if self.period_type == PERIOD_TYPES[1][0] else timedelta(days=+1)
        for i in range(1, self.period):

            start_date = start_date +timeD
            end_date = end_date +timeD
            child = super(Event,self.parent_id).copy(self.parent_id,{"start_date":start_date,"end_date":end_date,"name":name + "%d"%i})
            
        return {}
