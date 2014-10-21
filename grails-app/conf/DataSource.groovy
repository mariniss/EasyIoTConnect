dataSource {
	pooled = true
	jmxExport = true
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
	development {

		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			driverClassName = "org.postgresql.Driver"
			dialect = net.sf.hibernate.dialect.PostgreSQLDialect
			pooled=true
			username = "postgres"
			password = "postgres"
			url = "jdbc:postgresql://localhost:5432/easyiotconnect?autoreconnect=true"
		}
	}
	test {
		dataSource {
			driverClassName = "org.h2.Driver"
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			driverClassName = "org.postgresql.Driver"
			dialect = net.sf.hibernate.dialect.PostgreSQLDialect
			url = "jdbc:postgresql://ec2-79-125-17-195.eu-west-1.compute.amazonaws.com:5432/ddhkqlf5vd5e99"
			username = "dxcskcoeewdehg"
			password = "Ok7c26ZGET7BI-M7oz6xobeF3n"
			properties {
				// See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
				jmxEnabled = true
				initialSize = 5
				maxActive = 50
				minIdle = 5
				maxIdle = 25
				maxWait = 10000
				maxAge = 10 * 60000
				timeBetweenEvictionRunsMillis = 5000
				minEvictableIdleTimeMillis = 60000
				validationQuery = "SELECT 1"
				validationQueryTimeout = 3
				validationInterval = 15000
				testOnBorrow = true
				testWhileIdle = true
				testOnReturn = false
				jdbcInterceptors = "ConnectionState"
				defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
			}
		}
	}
}
