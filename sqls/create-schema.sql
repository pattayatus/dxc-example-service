create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
create sequence hibernate_sequence start with 1 increment by 1

    create table tsi_person_contact_entity (
       person_id bigint not null,
        given_name varchar(255),
        sur_name varchar(255),
        primary key (person_id)
    )

    create table sam.dbo.view_group_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_group_id bigint,
        user_group_name varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table sam.dbo.view_user_data_acl (
       id bigint not null,
        allow_value varchar(255),
        dataset_id varchar(255),
        dataset_organization_id varchar(255),
        group_id bigint,
        group_name varchar(255),
        tag varchar(255),
        user_citizen_card_number varchar(255),
        user_organization_id varchar(255),
        user_username varchar(255),
        primary key (id)
    )

    create table dxc_qm_query_log (
       id integer not null,
        authen_id varchar(255),
        cache_id varchar(255),
        client_request_params varchar(255),
        client_request_uri varchar(255),
        criteria varchar(255),
        data_code varchar(255),
        environment varchar(255),
        is_from_cache boolean not null,
        max_number_of_results integer,
        number_of_results integer,
        offset integer,
        order_by varchar(255),
        owner_department_code varchar(255),
        owner_department_name varchar(255),
        qm_error_code varchar(255),
        qm_error_description varchar(255),
        qm_error_detail varchar(255),
        qm_error_note varchar(255),
        qm_error_type varchar(255),
        qm_log_app varchar(255),
        query_string varchar(255),
        query_time timestamp,
        query_type varchar(255),
        requester_access_date_time varchar(255),
        requester_access_resource varchar(255),
        requester_global_transaction_id varchar(255),
        requester_local_transaction_id varchar(255),
        requester_token varchar(255),
        responder_transaction_id varchar(255),
        response_code varchar(255),
        response_message varchar(255),
        response_time timestamp,
        save_qm_log_app varchar(255),
        server_code varchar(255),
        timestamp timestamp,
        total_number_of_results integer,
        total_page integer,
        user_citizen_id varchar(255),
        user_department_code varchar(255),
        userip varchar(255),
        user_id integer,
        user_name varchar(255),
        version varchar(255),
        xml varchar(255),
        primary key (id)
    )

    create table qm_audit_log_entity (
       audit_log_id bigint not null,
        comment varchar(255),
        data_set_id varchar(255),
        end_date_time timestamp,
        query_parameters varchar(255),
        result_size integer,
        result_status varchar(255),
        start_date_time timestamp,
        user_name varchar(255),
        user_thai_nin varchar(255),
        primary key (audit_log_id)
    )

    create table house_entity (
       job_code varchar(255) not null,
        name varchar(255),
        primary key (job_code)
    )
