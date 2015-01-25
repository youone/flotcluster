'''
Created on Oct 26, 2014

@author: johan
'''#!/usr/bin/env python
# -*- coding: UTF-8 -*-

from cgi import escape
#import sys, os
from flup.server.fcgi import WSGIServer

def app(environ, start_response):
    start_response('200 OK', [('Content-Type', 'text/html')])

    yield '<h1>FastCGI Environment</h1>'
    yield '<table>'
    for k, v in sorted(environ.items()):
        yield '<tr><th>%s</th><td>%s</td></tr>' % (escape(k), escape(v))
    yield '</table>'

WSGIServer(app,bindAddress=('127.0.0.1',9001)).run()


# if __name__ == '__main__':
# 
#     
#     pass