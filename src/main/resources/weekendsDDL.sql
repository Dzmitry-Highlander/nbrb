DROP TABLE IF EXISTS app.WEEKENDS cascade;

CREATE TABLE app.WEEKENDS (
                              CALENDAR_DATE DATE NULL,
                              IS_DAY_OFF INTEGER  NULL,
                              CONSTRAINT PK_WEEKEND12345687 PRIMARY KEY (CALENDAR_DATE)
);