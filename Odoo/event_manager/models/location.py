from odoo import fields, api, models
from odoo.exceptions import ValidationError


class Location(models.Model):

    _name = "eventmanager.location"

    name = fields.Char(string="Name",
                       required=True, size=30
                       )

    town = fields.Char(string='Town',
                       required=True,
                       size=32,

                       )

    street = fields.Char(string='Street',
                         required=True,
                         size=32,

                         )

    number = fields.Integer(string='Number',
                            required=True, size=5,
                            )

    @api.constrains('number')
    def _check_number(self):
        if(self.number < 0):
            raise ValidationError('number must be positive')
