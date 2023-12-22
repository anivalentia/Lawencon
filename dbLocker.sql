CREATE database dbLocker;

create schema locker_schema;

create table "locker_schema".mst_user(
	userId bigint primary key not null generated always as identity,
	ktpNumber varchar(16),
	phoneNumber varchar(20),
	email varchar(50)
);

create table "locker_schema".lockers(
	lockerId bigint primary key not null generated always as identity,
	isAvailable boolean,
	isLock boolean,
	userId bigint
);

create table "locker_schema".transactions(
	bookingId bigint primary key not null generated always as identity,
	userId bigint,
	lockerId bigint,
	bookingDate date,
	deposit bigInt,
	status varchar(10),
	qtyAccess int,
	token varchar(50),
	isLocked boolean,
	foreign key(userId) references "locker_schema".mst_user(userId),
	foreign key(lockerId) references "locker_schema".lockers(lockerId)
);

create table "locker_schema".returns(
	bookingId bigint primary key not null,
	userId bigint,
	lockerId bigint,
	bookingDate date,
	returnDate date,
	holdDeposit bigInt,
	penaltyAmount bigint,
	isLocked boolean,
	isLate boolean,
	paidPenalty bigint,
	foreign key(userId) references "locker_schema".mst_user(userId),
	foreign key(lockerId) references "locker_schema".lockers(lockerId)
);