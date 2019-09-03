FROM ubuntu
ADD https://github.com/edureka-devops/projCert.git /var/www/html
# RUN rm /var/www/html/index.html
CMD apachectl -D FOREGROUND

