CREATE TABLE `organization` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `owner` varchar(100) NOT NULL,
  `creator` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `name` varchar(100) NOT NULL,
  `org_id` varchar(36) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `stage` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
   PRIMARY KEY (`name`),
   CONSTRAINT `org_fk_role` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `role_permission` (
  `role` varchar(36) NOT NULL,
  `action` varchar(100) NOT NULL,
  `resource` varchar(100) NOT NULL,
  `org_id` varchar(36) NOT NULL,
  CONSTRAINT pk_PersonID PRIMARY KEY (`role`,`action`, `resource`),
  CONSTRAINT `system_fk_role` FOREIGN KEY (`role`) REFERENCES `role` (`name`),
  CONSTRAINT `org_fk_role_per` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `role` varchar(36) NOT NULL,
  `user` varchar(100) NOT NULL,
  `org_id` varchar(36) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  CONSTRAINT pk_role_user PRIMARY KEY (`role`,`user`, `org_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role`) REFERENCES `role` (`name`),
  CONSTRAINT `org_fk_org_id` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

