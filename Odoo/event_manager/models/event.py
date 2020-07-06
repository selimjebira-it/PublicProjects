# -*- coding: utf-8 -*-
from odoo import fields, api, models
from odoo.exceptions import ValidationError
from datetime import datetime

class Event(models.Model):

    _name = "eventmanager.event"

    # name of the event
    name = fields.Char(size=32, string="Name", required=True)

    diary_id = fields.Many2one('eventmanager.diary', string='Diary',required=True)

    # Description of the Event
    description = fields.Text(string='Description', required=True, size=240)

    # location of the Event
    location_id = fields.Many2one(
        "eventmanager.location", string='Location', required=True)

    # date of the start of the event
    start_date = fields.Datetime(string="Begin", required=True)
    # date of the end of the event
    end_date = fields.Datetime(string="End", required=True)
    # Attendees of the event
    attendees_ids = fields.Many2many("res.users", string="Attendees")

    # author of the event
    author_id = fields.Many2one(
        'res.users', string='owner', default=lambda self: self.env.user.id, readonly=True,
        ondelete='cascade')

    parent_id = fields.Many2one('eventmanager.event', string='Parent')

    children_ids = fields.One2many(
        'eventmanager.event', 'parent_id', string='Children')

    max_attendees = fields.Integer(string='Maximum of attendees',required=True ,default=50)


    @api.one
    @api.constrains('attendees_ids')
    def _constrains_max_attendees(self):
        if(len(self.attendees_ids) > self.max_attendees):
            raise ValidationError("to much attendees")

    @api.one
    @api.constrains('start_date', 'end_date')
    def _constrains_start_date(self):
        if(datetime.strptime(self.end_date,'%Y-%m-%d %H:%M:%S')  < datetime.strptime(self.start_date,'%Y-%m-%d %H:%M:%S')):
            raise ValidationError("starting date must be before the end date")
        # if(datetime.strptime(self.start_date, '%Y-%m-%d %H:%M:%S') < datetime.now()):
        #     raise ValidationError("starting date must bein the future")


    
    # @api.multi
    # def write(self, values):

    #     if(self.env.user.id != self.author_id.id):
    #         raise ValidationError("You cannot modify this")
    #     return super(Event, self).write(values)

    @api.multi
    def delete(self):
        if(self.env.user.id != self.author_id.id):
            raise ValidationError("You cannot delete this")
        return super(Event, self).delete()

    
    @api.multi
    def copy(self, default=None):
        """
            Create a new record in event model from existing one
            @param default: dict which contains the values to be override during
            copy of object
    
            @return: returns a id of newly created record
        """
        
        
        result = super(Event, self).copy(default)
        return result
    


class UserEventExt(models.Model):

    _inherit = 'res.users'

    event_ids = fields.One2many(
        'eventmanager.event', 'author_id', string='Events',
        ondelete='set null'
    )


class LocationEventExt(models.Model):

    _inherit = "eventmanager.location"

    event_ids = fields.One2many(
        'eventmanager.event', 'location_id', string='Events',
        ondelete='set null'
    )
