package com.vet.mackolec.services;

import com.vet.mackolec.events.HeartBeatEvent;

public interface HeartBeatService {

	void resonate(HeartBeatEvent heartBeatEvent);
	
}
