package iot.db.helper.dynamodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;



public class DynamoDbMapperHelper<M>  extends DynamoDbHelper {
	protected Class<M> clazz;

	public DynamoDbMapperHelper(String tableName, Class<M> m) {
		super(tableName, m);
		this.clazz = m;
	}
	
	public DynamoDbMapperHelper(String tableName, Class<M> m, DynamoDBMapperConfig config) {
		super(tableName, m, config);
		this.clazz = m;
	}
	public M loadByPk(Object pk){
		return mapper.load(clazz, pk);
	}
	
	public boolean existsByPk(Object pk) {
		return loadByPk(pk) != null;
	}
	
	public void saveItem(M model){
		 mapper.save(model);
	}
	
	public void saveItemByConfig(M model,DynamoDBMapperConfig config){
		 mapper.save(model, config);
	}
	
	public void batchWrite(List<M> modelToSave,List<M> modelToDelete,DynamoDBMapperConfig config){
		 mapper.batchWrite(modelToSave, modelToDelete, config);
	}
	
	public void deleteItem(M model){
		 mapper.delete(model);
	}
	
	public List<M> scanAttributeN(String atr, String val){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(val));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression(atr+" = :val")
            .withExpressionAttributeValues(eav);
        
        List<M> scanResult = mapper.scan(clazz, scanExpression);
        return scanResult;
	}
	
	public List<M> scanAttributeN(String atr, String val, DynamoDBMapperConfig config){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(val));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression(atr+" = :val")
            .withExpressionAttributeValues(eav);
        
        List<M> scanResult = mapper.scan(clazz, scanExpression, config);
        return scanResult;
	}
	
	public List<M> scanAttributeN(String atr, String val, String from, String to, DynamoDBMapperConfig config){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(val));
        eav.put(":val2", new AttributeValue().withN(from));
        eav.put(":val3", new AttributeValue().withN(to));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression(atr+" = :val and #t between :val2 and :val3")
            .withExpressionAttributeValues(eav);
        
        Map<String, String> expression = new HashMap<>();
        expression.put("#t", "timestamp");
        scanExpression.withExpressionAttributeNames(expression);
       
        List<M> scanResult = mapper.scan(clazz, scanExpression, config);
        return scanResult;
	}
	
	public List<M> batchLoad(String partitionKey,String val){
		 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		    eav.put(":val", new AttributeValue().withS(val));
		    DynamoDBQueryExpression<M> queryExpression = new DynamoDBQueryExpression<M>()
		                             .withKeyConditionExpression(partitionKey+" = :val")
		                             .withExpressionAttributeValues(eav);

		 return mapper.query(clazz, queryExpression);
	}
	
	public List<M> batchLoad(String partitionKey,String val, DynamoDBMapperConfig config){
		 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		    eav.put(":val", new AttributeValue().withS(val));
		    DynamoDBQueryExpression<M> queryExpression = new DynamoDBQueryExpression<M>()
		                             .withKeyConditionExpression(partitionKey+" = :val")
		                             .withExpressionAttributeValues(eav);
		    
		 return mapper.query(clazz, queryExpression, config);
	}
	
	public List<M> queryWithinRangeKey(String partitionKey, String val, String from, String to, int limit, DynamoDBMapperConfig config){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(val));
        if(from!=null)
        		eav.put(":val2", new AttributeValue().withN(from));
        if(to!=null)
        		eav.put(":val3", new AttributeValue().withN(to));
        DynamoDBQueryExpression<M> queryExpression = new DynamoDBQueryExpression<M>();
        if(from!=null && to !=null) {
        		queryExpression.withKeyConditionExpression(partitionKey+" = :val and #t between :val2 and :val3")
        		.withExpressionAttributeValues(eav);
        }else if(from==null) {
    			queryExpression.withKeyConditionExpression(partitionKey+" = :val and #t <= :val3")
    			.withExpressionAttributeValues(eav);
        }else if(to==null) {
			queryExpression.withKeyConditionExpression(partitionKey+" = :val and #t >= :val2")
			.withExpressionAttributeValues(eav);
        }
        
        
        Map<String, String> expression = new HashMap<>();
        expression.put("#t", "timestamp");
        queryExpression.withExpressionAttributeNames(expression);
        if(limit!=0)
        		queryExpression.withLimit(limit).setScanIndexForward(false);;
        List<M> queryResult = mapper.query(clazz, queryExpression, config);
        return queryResult;
	}
	
}
