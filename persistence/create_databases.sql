-- Local environment
CREATE USER gadgets LOGIN PASSWORD 'gadgets';
CREATE DATABASE gadgets OWNER gadgets;
-- Test environment
CREATE USER gadgets_test LOGIN PASSWORD 'gadgets_test';
CREATE DATABASE gadgets_test OWNER gadgets_test;