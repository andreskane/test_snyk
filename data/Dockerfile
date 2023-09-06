FROM mcr.microsoft.com/mssql/server:2019-CU8-ubuntu-16.04
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Password123
COPY ./data /
ENTRYPOINT [ "/bin/bash", "entrypoint.sh" ]
CMD [ "/opt/mssql/bin/sqlservr" ]