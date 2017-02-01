################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src-gen/statemachine/EventPriorityQueue.cpp \
../src-gen/statemachine/Event_t.cpp \
../src-gen/statemachine/StructForThread_t.cpp 

OBJS += \
./src-gen/statemachine/EventPriorityQueue.o \
./src-gen/statemachine/Event_t.o \
./src-gen/statemachine/StructForThread_t.o 

CPP_DEPS += \
./src-gen/statemachine/EventPriorityQueue.d \
./src-gen/statemachine/Event_t.d \
./src-gen/statemachine/StructForThread_t.d 


# Each subdirectory must supply rules for building sources it contributes
src-gen/statemachine/%.o: ../src-gen/statemachine/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"/export/home/ansgar/workspaces/neon/runtime-New_configurationClean/SimpleSM_defaultNode_SimpleSMDepPlan/src-gen" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


