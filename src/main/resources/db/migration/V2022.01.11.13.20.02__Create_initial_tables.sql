create table cargo
(
    cargo_id                          bigint auto_increment
        primary key,
    booking_amount                    int          null,
    booking_id                        varchar(255) null,
    current_voyage_id                 varchar(255) null,
    last_handled_event_id             int          null,
    last_known_location_id            varchar(255) null,
    next_expected_location_id         varchar(255) null,
    next_expected_handling_event_type varchar(255) null,
    voyage_id                         varchar(255) null,
    routing_status                    varchar(255) null,
    transport_status                  varchar(255) null,
    origin_id                         varchar(255) null,
    spec_arrival_deadline             datetime(6)  null,
    spec_destination_id               varchar(255) null,
    spec_origin_id                    varchar(255) null
);

create table leg
(
    cargo_id           bigint       not null,
    load_location_id   varchar(255) null,
    load_time          datetime(6)  null,
    unload_location_id varchar(255) null,
    unload_time        datetime(6)  null,
    voyage_number      varchar(255) null
);
