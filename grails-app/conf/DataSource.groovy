dataSource {
	pooled = true
	jmxExport = true
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
	fetchType=EAGER
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
			driverClassName = "org.postgresql.Driver"
	        url = "jdbc:postgresql://ec2-54-247-79-142.eu-west-1.compute.amazonaws.com:5432/dbch2r4r2u49pq"
			username = "cgurzxmibfdpxi"
			password = "eKcQJP0tZ8uVIQR-ZQY005MMYq"
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
