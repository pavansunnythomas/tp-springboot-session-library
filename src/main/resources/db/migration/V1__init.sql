
    create table `book_ledger` (
        `created_date` date not null,
        `due_date` date not null,
        `issue_date` date not null,
        `return_date` date,
        `book_id` bigint not null,
        `ledger_id` bigint not null auto_increment,
        `user_id` bigint not null,
        `remarks` varchar(255),
        `status` enum ('ISSUED','LOST','OVERDUE','RETURNED') not null,
        primary key (`ledger_id`)
    ) engine=InnoDB;

    create table `books` (
        `added_date` date not null,
        `available` bit not null,
        `available_copies` integer not null,
        `price` float(53),
        `publication_year` integer,
        `total_copies` integer not null,
        `book_id` bigint not null auto_increment,
        `isbn` varchar(20) not null,
        `language` varchar(50),
        `category` varchar(100),
        `author` varchar(150) not null,
        `publisher` varchar(150),
        `title` varchar(200) not null,
        primary key (`book_id`)
    ) engine=InnoDB;

    create table `user` (
        `active` bit not null,
        `created_date` date not null,
        `currently_borrowed_count` integer not null,
        `max_books_allowed` integer not null,
        `membership_start_date` date not null,
        `user_id` bigint not null auto_increment,
        `phone_number` varchar(20),
        `membership_number` varchar(50) not null,
        `email` varchar(150) not null,
        `full_name` varchar(150) not null,
        primary key (`user_id`)
    ) engine=InnoDB;

    create index idx_ledger_book_status 
       on `book_ledger` (`book_id`, `status`);

    create index idx_ledger_user_status 
       on `book_ledger` (`user_id`, `status`);

    alter table `books` 
       add constraint `UKfk0d0dgf8wq1lm1lqkaic7jw9` unique (`isbn`);

    alter table `user` 
       add constraint `UKoshmjvr6wht0bg9oivn75aajr` unique (`email`);

    alter table `user` 
       add constraint `UKe6inkeoxpi5hd3w68m0n20dj1` unique (`membership_number`);

    alter table `book_ledger` 
       add constraint `FKi989l7o46h31tcyh2hag0jehs` 
       foreign key (`book_id`) 
       references `books` (`book_id`);

    alter table `book_ledger` 
       add constraint `FK6paf6g9o0u0s2y79tltk3hod4` 
       foreign key (`user_id`) 
       references `user` (`user_id`);
