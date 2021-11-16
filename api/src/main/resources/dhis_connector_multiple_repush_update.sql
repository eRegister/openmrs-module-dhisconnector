alter table dhisconnector_report_to_dataset add column last_push_retry datetime;
alter table dhisconnector_report_to_dataset add column retry_count int(11);
