# -*- coding: utf-8 -*-
{
    'name': "EventManager",

    'summary': """
        Event Manager
        """,

    'description': """
        Event Manager, application to handle events
    """,

    'author': "My Company",
    'website': "http://www.yourcompany.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/11.0/odoo/addons/base/module/module_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/security_group.xml',
        'security/ir.model.access.csv',
        'views/eventmanager.xml',
        'views/location.xml',
        'views/course.xml',
        'views/event.xml',
        'views/seance.xml',
        'views/diary.xml',
        'views/eventwizardchildren.xml',
        'views/eventwizardsubscribe.xml',
        'views/eventwizarddiary.xml',
        'views/diarywizard.xml',
        'report/product_template_report_templates.xml',
        'data/user.xml',
        'data/location.xml',
        'data/course.xml',
        'data/diary.xml',
        'data/seance.xml',

    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
    'application': True,
    'installable': True,
}
