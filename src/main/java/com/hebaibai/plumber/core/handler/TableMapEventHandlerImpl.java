package com.hebaibai.plumber.core.handler;

import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.hebaibai.plumber.core.utils.EventDataUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 监控表的名称
 * TableMap
 *
 * @author hjx
 */
@Slf4j
public class TableMapEventHandlerImpl extends AbstractEventHandler implements EventHandler {

    public TableMapEventHandlerImpl() {
    }

    @Override
    public boolean support(EventType eventType, String dataBaseName, String tableName) {
        if (EventType.TABLE_MAP != eventType) {
            return false;
        }
        return status;
    }

    @Override
    public void handle(EventData data) {
        TableMapEventData tableMapEventData = EventDataUtils.getTableMapEventData(data);
        long tableId = tableMapEventData.getTableId();
        String tableName = tableMapEventData.getTable();
        String databaseName = tableMapEventData.getDatabase();
        auth.seveTableName(tableId, tableName);
        auth.seveDatabaseName(tableId, databaseName);
    }


    @Override
    public String getName() {
        return name;
    }
}