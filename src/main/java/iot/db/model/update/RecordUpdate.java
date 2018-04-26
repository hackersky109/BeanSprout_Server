package iot.db.model.update;


import iot.db.helper.dynamodb.update.RecordUpdateDynamoHelper;
import iot.db.model.Record;
import iot.service.api.bean.RecordBean;


public class RecordUpdate {
	private RecordUpdateDynamoHelper updateHelper;
	
	public RecordUpdate() {
		updateHelper = new RecordUpdateDynamoHelper("Bean_Record");
	}
	
	public Record create(RecordBean bean, double d) {
//		if(!bean.isValid()) 
//			throw new BadRequestException("create record failed due to empty fields or wrong sensertype");
		Record rd = new Record(bean);
		rd.setInitialValue(d);
		updateHelper.saveItem(rd);
		return rd;
	}
	
	public Record updateRecord(Record rd) {
		updateHelper.saveItem(rd);
		return rd;
	}
	
	public Record deleteRecord(Record rd) {
		updateHelper.deleteItem(rd);
		return rd;
	}

}
