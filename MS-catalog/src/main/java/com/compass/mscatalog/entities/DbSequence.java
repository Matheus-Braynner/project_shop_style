package com.compass.mscatalog.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "db_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbSequence {
	
	@Id
	private String id;
	private Long seq;
}
