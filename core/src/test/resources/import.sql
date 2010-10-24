--DELETE FROM SCHEMA_INFO;
--DELETE FROM ENTRY_VALUES;
--DELETE FROM ATTRIB_VALUES;
--DELETE FROM ATTRIBS;
--DELETE FROM ENTRIES;
--DELETE FROM ENTRY_TYPES_ATTRIB_TYPES;
--DELETE FROM ATTRIB_TYPES;
--DELETE FROM ENTRY_TYPES;


-- Create the basic entry types
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(1,'String','F',NULL,NOW,NOW);
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(12,'Number','F',NULL,NOW,NOW);
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(13,'Date','F',NULL,NOW,NOW);


-- Construct a Full Name entry type and assign it two attribute types
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(2,'Full Name','T','#{First Name} #{Last Name}',NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(1,'First Name','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(2,1);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(2,'Last Name','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(2,2);


-- Create our first entry, a Full Name
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(1,2,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(1,1,1,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(1,'Jarrod');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(2,1,2,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(2,'Carlson');


-- Now create a Person entry type, and make Full Name and attribute type for it
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(3,'Person','T','#{Full Name}',NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(3,'Full Name','F',2,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(3,3);


-- Create the first Person, and assign the existing Full Name to it
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(2,3,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(3,2,3,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(3,1);


-- Create an Address entry type, and give it some attribute types
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(7,'Address','T','#{Address 1}, #{City}',NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(4,'Address 1','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(7,4);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(5,'Address 2','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(7,5);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(6,'City','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(7,6);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(7,'State','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(7,7);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(8,'Zip','F',12,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(7,8);


-- Now create attribute types out of the Address entry type and attach them to Person
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(9,'Home Address','F',7,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(3,9);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(10,'Work Address','F',7,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(3,10);


-- And create our first Address
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(7,7,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(20,7,4,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(20,'508 Main St NE');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(21,7,5,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(21,'APT 3358');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(22,7,6,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(22,'Atlanta');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(23,7,7,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(23,'GA');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(24,7,8,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(24,'30324');


-- Attach the new Address entry to our Person
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(19,2,9,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(19,7);


-- Also create a Phone Number entry type
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(6,'Phone Number','T','#{Telephone Digits} (#{Label})',NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(11,'Telephone Digits','F',12,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(6,11);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(12,'Label','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(6,12);


-- Create a Phone Number entry
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(4,6,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(11,4,11,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(11,'404-358-4221');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(12,4,12,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(12,'Blackberry');


-- Now create a Phone Number attribute type so we can attach it to our person
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(13,'Phone Number','F',6,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(3,13);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(13,2,13,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(13,4);


-- Create a work address now to finish out the Person
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(8,7,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(25,8,4,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(25,'1050 Techwood Dr');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(26,8,5,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(26,'');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(27,8,6,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(27,'Atlanta');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(28,8,7,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(28,'GA');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(29,8,8,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(29,'30318');

-- And attach the new work address
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(30,2,10,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(30,8);



-- Now we will construct a second person
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(6,3,NOW,NOW); -- Person
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(3,2,NOW,NOW); -- Full Name
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(9,3,1,NOW,NOW); -- first name
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(9,'Tom');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(10,3,2,NOW,NOW); -- last name
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(10,'Smith');
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(14,6,3,NOW,NOW); -- attach to person
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(14,3);


-- Create a Phone Call entry type
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(10,'Phone Call','T','#{Caller} called #{Callee} at #{Phone Number}',NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(14,'Caller','F',3,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(10,14);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(15,'Callee','F',3,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(10,15);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(10,13);


-- Now let's make our first call
INSERT INTO ENTRIES (id, entry_type_id, created_at, updated_at) VALUES(5,10,NOW,NOW);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(16,5,14,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(16,2);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(17,5,15,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(17,6);
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(18,5,13,NOW,NOW);
INSERT INTO ENTRY_VALUES (attrib_id, entry_id) VALUES(18,4);


-- Finally, we're creating a Calendar Entry entry type
INSERT INTO ENTRY_TYPES (id, name, complex, template, created_at, updated_at) VALUES(11,'Calendar Entry','T',NULL,NOW,NOW);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(16,'Start Date and Time','F',13,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,16);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(17,'Subject','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,17);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(18,'Location','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,18);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(19,'Location','F',7,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,19);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(20,'Details','F',1,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,20);
INSERT INTO ATTRIB_TYPES (id, name, collection, entry_type_id, created_at, updated_at) VALUES(21,'End Date and Time','F',13,NOW,NOW);
INSERT INTO ENTRY_TYPES_ATTRIB_TYPES (entry_type_id, attrib_type_id) VALUES(11,21);



-- Create an ad-hoc attribute association
INSERT INTO ATTRIBS (id, entry_id, attrib_type_id, created_at, updated_at) VALUES(31,2,12,NOW,NOW);
INSERT INTO ATTRIB_VALUES (attrib_id, attrib_value) VALUES(31,'Blackberry');



-- Detail the Schema version
INSERT INTO SCHEMA_INFO (version, notes) VALUES(1,'Initial DDL import');
