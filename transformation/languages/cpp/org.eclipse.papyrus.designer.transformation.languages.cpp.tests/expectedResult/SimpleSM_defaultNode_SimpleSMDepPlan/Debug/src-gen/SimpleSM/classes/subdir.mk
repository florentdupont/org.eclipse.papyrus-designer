################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src-gen/SimpleSM/classes/SimpleSM.cpp 

OBJS += \
./src-gen/SimpleSM/classes/SimpleSM.o 

CPP_DEPS += \
./src-gen/SimpleSM/classes/SimpleSM.d 


# Each subdirectory must supply rules for building sources it contributes
src-gen/SimpleSM/classes/%.o: ../src-gen/SimpleSM/classes/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"/export/home/ansgar/workspaces/neon/runtime-New_configurationClean/SimpleSM_defaultNode_SimpleSMDepPlan/src-gen" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


