# -*- coding: utf-8 -*-
###############################################################################
#    License, author and contributors information in:                         #
#    __manifest__.py file at the root folder of this module.                  #
###############################################################################

from odoo.http import route, request, Controller, json
from odoo.exceptions import UserError, ValidationError


class EventController(Controller):
    """ Api for event

        Routes:
          /api/events: list of all events
    """
    @route('/api/events', method=["GET"])
    def api_events(self, **kw):

        return json.dumps({"events": [{"name": event.name, "description": event.description, "start_date": event.start_date, "end_date": event.end_date, "location_id": event.location_id.id, "author_id": event.author_id.id} for event in request.env["eventmanager.event"].search([])]})


class CourseController(Controller):

    # api for courses
    @route('/api/courses', method=["GET"])
    def api_courses(self, **kw):
        return json.dumps({"courses": [{"slug": course.slug, "ects": course.ects} for course in request.env["eventmanager.course"].search([])]})


class DiaryController(Controller):

    # api for courses
    @route('/api/diaries', method=["GET"])
    def api_diaries(self, **kw):
        return json.dumps({"diaries": [{"name": diary.name, "owner_id": diary.owner_id.id, "event_ids": [event.id for event in diary.event_ids], "members.ids":[member.id for member in diary.member_ids], "diary_type":diary.diary_type, "is_organizer":diary.is_organizer} for diary in request.env["eventmanager.diary"].search([])]})


class LocationController(Controller):

    # api for courses
    @route('/api/locations', method=["GET"])
    def api_locations(self, **kw):
        return json.dumps({"locations": [{"name":location.name,"town":location.town,"street":location.street,"number":location.number} for location in request.env["eventmanager.location"].search([])]})
