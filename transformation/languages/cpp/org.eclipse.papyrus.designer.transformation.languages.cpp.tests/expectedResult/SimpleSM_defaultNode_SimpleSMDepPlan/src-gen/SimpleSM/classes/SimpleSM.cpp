// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#define SimpleSM_classes_SimpleSM_BODY

/************************************************************
 SimpleSM class body
 ************************************************************/

// include associated header file
#include "SimpleSM/classes/SimpleSM.h"

// Derived includes directives
#include "statemachine/Event_t.h"
#include "statemachine/Pkg_statemachine.h"
#include "sysinterfaces/IStart.h"

// Include from Include declaration (body)
#include <iostream>
using namespace std;

#include <unistd.h>

// End of Include declaration (body)

namespace SimpleSM {
namespace classes {

// static attributes (if any)

/**
 * 
 */
void SimpleSM::dispatchEvent() {
	bool popDeferred = false;
	while (true) {
		//run-to-completion: need to have a mutex here
		currentEvent = eventQueue.pop(popDeferred);
		dispatchFlag = true;
		if (currentEvent != NULL) {
			SIMPLESM_GET_CONTROL
			switch (currentEvent->eventID) {
			case TE_VALUE_250_UNIT_MS__ID:
				processTE_value_250_unit_ms_();
				break;
			case TE_VALUE_500_UNIT_MS__ID:
				processTE_value_500_unit_ms_();
				break;
			case COMPLETIONEVENT_ID:
				processCompletionEvent();
				break;
			}
			if (systemState == statemachine::EVENT_DEFERRED) {
				eventQueue.saveDeferred(*currentEvent);
			}
			popDeferred = (systemState != statemachine::EVENT_DEFERRED);
			systemState = statemachine::IDLE;
			SIMPLESM_RELEASE_CONTROL
		}
	}
}

/**
 * 
 * @param a 
 * @param b 
 * @return res 
 */
::PrimitiveTypes::Integer SimpleSM::add(::PrimitiveTypes::Integer /*in*/a,
		::PrimitiveTypes::Integer /*in*/b) {
	this->processCE_CServer_impl_add(a, b);
	// original method code
	cout << "a=" << a << " b=" << b << " a+b=" << a + b << endl;
	return a + b;
}

/**
 * 
 */
void SimpleSM::run() {
	cout << "call add (2, 3);" << endl;
	cout << "result: " << add(2, 3) << endl;
	cout << "sleeping for 15 seconds (statemachine remains active);" << endl;
	sleep(15);
}

/**
 * 
 * @param enter_mode 
 */
void SimpleSM::SMSimple_Region0_Enter(char /*in*/enter_mode) {
	switch (enter_mode) {
	case SMSIMPLE_REGION0_DEFAULT:
		activeStateID = FLIP_ID;

		setFlag(SIMPLESM_TE_INDEX(TE_VALUE_250_UNIT_MS__ID),
				statemachine::TF_TIME_EVENT, true);

		//TODO: set systemState to EVENT_CONSUMED
		break;
	}
}

/**
 * 
 */
SimpleSM::SimpleSM() {
	startBehavior();
}

/**
 * 
 */
void SimpleSM::startBehavior() {
	systemState = statemachine::IDLE;
	doActivityTable[FLIP_ID] = states[FLIP_ID].doActivity;
	doActivityTable[FLOP_ID] = states[FLOP_ID].doActivity;

	// initialize all threads, the threads wait until the associated flag is set
	for (int i = 0; i < (int) STATE_MAX; i++) {
		if (states[i].doActivity != &SimpleSM::doActivity_dft) {
			threadStructs[i].id = i;
			threadStructs[i].ptr = this;
			threadStructs[i].func_type = statemachine::TF_DO_ACTIVITY;
			mutexes[i] = PTHREAD_MUTEX_INITIALIZER;
			conds[i] = PTHREAD_COND_INITIALIZER;
			pthread_create(&threads[i], NULL, &SimpleSM::thread_func_wrapper,
					&threadStructs[i]);
		}
	}

	timeEventThreadStructs[SIMPLESM_TE_INDEX(TE_VALUE_250_UNIT_MS__ID)].duration =
			250;
	timeEventThreadStructs[SIMPLESM_TE_INDEX(TE_VALUE_500_UNIT_MS__ID)].duration =
			500;
	for (int i = SIMPLESM_TIME_EVENT_LOWER_BOUND; i < 2; i++) {
		timeEventThreadStructs[SIMPLESM_TE_INDEX(i)].id = i;
		timeEventThreadStructs[SIMPLESM_TE_INDEX(i)].ptr = this;
		timeEventThreadStructs[SIMPLESM_TE_INDEX(i)].func_type =
				statemachine::TF_TIME_EVENT;
		timeEventMutexes[SIMPLESM_TE_INDEX(i)] = PTHREAD_MUTEX_INITIALIZER;
		timeEventConds[SIMPLESM_TE_INDEX(i)] = PTHREAD_COND_INITIALIZER;
		pthread_create(&timeEventThreads[SIMPLESM_TE_INDEX(i)], NULL,
				&SimpleSM::thread_func_wrapper,
				&timeEventThreadStructs[SIMPLESM_TE_INDEX(i)]);
		while (timeEventFlags[SIMPLESM_TE_INDEX(i)]) {
		}
	}

	runToCompletionMutex = PTHREAD_MUTEX_INITIALIZER;
	runToCompletionCond = PTHREAD_COND_INITIALIZER;

	dispatchStruct = statemachine::StructForThread_t(this, 0, 0,
			statemachine::TF_STATE_MACHINE_TYPE, 0);
	SimpleSM_THREAD_CREATE(dispatchThread, dispatchStruct)
	while (!dispatchFlag) {
	}

	//initialze root active state
	//execute initial effect
	SMSimple_Region0_Enter (SMSIMPLE_REGION0_DEFAULT);
}

/**
 * 
 * @param a 
 * @param b 
 */
void SimpleSM::processCE_CServer_impl_add(::PrimitiveTypes::Integer /*in*/a,
		::PrimitiveTypes::Integer /*in*/b) {
	SIMPLESM_GET_CONTROL systemState = statemachine::EVENT_PROCESSING;
	if (systemState == statemachine::EVENT_PROCESSING) {
		switch (activeStateID) {
		case FLIP_ID:
			//from Flip to Flop
			if (true) {
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_250_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, false);
				activeStateID = FLOP_ID;
				//starting the counters for time events
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_500_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, true);
				systemState = statemachine::EVENT_CONSUMED;
			}
			break;
		default:
			//do nothing
			break;
		}
	}
	SIMPLESM_RELEASE_CONTROL
}

/**
 * 
 */
void SimpleSM::processTE_value_250_unit_ms_() {
	systemState = statemachine::EVENT_PROCESSING;
	if (systemState == statemachine::EVENT_PROCESSING) {
		switch (activeStateID) {
		case FLIP_ID:
			//from Flip to Flop
			if (true) {
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_250_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, false);
				std::cout << "From Flip to Flop" << std::endl;
				activeStateID = FLOP_ID;
				//starting the counters for time events
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_500_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, true);
				systemState = statemachine::EVENT_CONSUMED;
			}
			break;
		default:
			//do nothing
			break;
		}
	}
}

/**
 * 
 */
void SimpleSM::processTE_value_500_unit_ms_() {
	systemState = statemachine::EVENT_PROCESSING;
	if (systemState == statemachine::EVENT_PROCESSING) {
		switch (activeStateID) {
		case FLOP_ID:
			//from Flop to Flip
			if (true) {
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_500_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, false);
				std::cout << "From Flop to Flip" << std::endl;
				activeStateID = FLIP_ID;
				//starting the counters for time events
				setFlag(SIMPLESM_TE_INDEX(TE_VALUE_250_UNIT_MS__ID),
						statemachine::TF_TIME_EVENT, true);
				systemState = statemachine::EVENT_CONSUMED;
			}
			break;
		default:
			//do nothing
			break;
		}
	}
}

/**
 * 
 */
void SimpleSM::processCompletionEvent() {
	systemState = statemachine::EVENT_PROCESSING;
}

/**
 * 
 * @param data 
 * @return ret 
 */
void* SimpleSM::thread_func_wrapper(void* /*in*/data) {
	statemachine::StructForThread_t* cptr =
			(statemachine::StructForThread_t*) data;
	SimpleSM* ptr = (SimpleSM*) cptr->ptr;
	switch (cptr->func_type) {
	case statemachine::TF_TIME_EVENT:
		ptr->listenTimeEvent(SIMPLESM_TE_INDEX(cptr->id), cptr->duration);
		break;
	case statemachine::TF_STATE_MACHINE_TYPE:
		ptr->dispatchEvent();
		break;
	}
	return NULL;
}

/**
 * 
 * @param id 
 */
void SimpleSM::doCallActivity(int /*in*/id) {
	flags[id] = false;
	while (true) {
		pthread_mutex_lock (&mutexes[id]);
		while (!flags[id]) {
			pthread_cond_wait(&conds[id], &mutexes[id]);
		}
		(this->*doActivityTable[id])();
		bool commitEvent = false;
		if (flags[id]) {
			commitEvent = true;
			flags[id] = false;
		}
		pthread_cond_signal (&conds[id]);
		pthread_mutex_unlock(&mutexes[id]);
	}
}

/**
 * 
 * @param id 
 * @param func_type 
 * @param value 
 */
void SimpleSM::setFlag(int /*in*/id, char /*in*/func_type, bool /*in*/value) {
	//value = true => start activity
	//value = false => stop activity
	if (func_type == statemachine::TF_TIME_EVENT) {
		pthread_mutex_lock (&timeEventMutexes[SIMPLESM_TE_INDEX(id)]);
		timeEventFlags[SIMPLESM_TE_INDEX(id)] = value;
		pthread_cond_signal (&timeEventConds[SIMPLESM_TE_INDEX(id)]);
		pthread_mutex_unlock(&timeEventMutexes[SIMPLESM_TE_INDEX(id)]);
		return;
	}
	if (func_type == statemachine::TF_DO_ACTIVITY) {
	}
}

/**
 * 
 * @param id 
 * @param duration 
 */
void SimpleSM::listenTimeEvent(int /*in*/id, int /*in*/duration) {
	struct timeval tv;
	struct timespec ts;
	int timedWaitResult;
	while (true) {
		pthread_mutex_lock (&timeEventMutexes[id]);
		while (!timeEventFlags[id]) {
			pthread_cond_wait(&timeEventConds[id], &timeEventMutexes[id]);
		}

		gettimeofday(&tv, NULL);
		ts.tv_sec = time(NULL) + duration / 1000;
		ts.tv_nsec = tv.tv_usec * 1000 + 1000 * 1000 * (duration % 1000);
		ts.tv_sec += ts.tv_nsec / (1000 * 1000 * 1000);
		ts.tv_nsec %= (1000 * 1000 * 1000);

		timedWaitResult = pthread_cond_timedwait(&timeEventConds[id],
				&timeEventMutexes[id], &ts);

		bool commitEvent = false;
		if (timedWaitResult != 0) {
			//timeout
			commitEvent = true;
		}
		timeEventFlags[id] = false;
		pthread_cond_signal (&timeEventConds[id]);
		pthread_mutex_unlock(&timeEventMutexes[id]);
		if (commitEvent) {
			//the state does not change, push time event to the queue
			eventQueue.push(statemachine::PRIORITY_2, NULL, id,
					statemachine::TIME_EVENT, id);
		}
	}
}

/**
 * 
 */
void SimpleSM::entry_dft() {
}

/**
 * 
 */
void SimpleSM::exit_dft() {
}

/**
 * 
 */
void SimpleSM::doActivity_dft() {
}

} // of namespace classes
} // of namespace SimpleSM

/************************************************************
 End of SimpleSM class body
 ************************************************************/
