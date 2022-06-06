package com.vet.mackolec.services;

import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;

public interface HeartBeatService {

	void resonate(HeartBeatEvent heartBeatEvent) throws HospitalizedCatException;
	
}
