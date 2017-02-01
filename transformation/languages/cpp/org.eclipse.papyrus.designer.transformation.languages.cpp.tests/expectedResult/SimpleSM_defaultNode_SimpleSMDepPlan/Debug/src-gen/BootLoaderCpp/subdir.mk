################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src-gen/BootLoaderCpp/BootLoader.cpp \
../src-gen/BootLoaderCpp/NodeInfo.cpp 

OBJS += \
./src-gen/BootLoaderCpp/BootLoader.o \
./src-gen/BootLoaderCpp/NodeInfo.o 

CPP_DEPS += \
./src-gen/BootLoaderCpp/BootLoader.d \
./src-gen/BootLoaderCpp/NodeInfo.d 


# Each subdirectory must supply rules for building sources it contributes
src-gen/BootLoaderCpp/%.o: ../src-gen/BootLoaderCpp/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"/export/home/ansgar/workspaces/neon/runtime-New_configurationClean/SimpleSM_defaultNode_SimpleSMDepPlan/src-gen" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


