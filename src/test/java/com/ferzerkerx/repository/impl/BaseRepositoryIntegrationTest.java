package com.ferzerkerx.repository.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestDbConfig.class})
@Transactional
//@Sql({"/schema.sql"})
abstract class BaseRepositoryIntegrationTest {

}
