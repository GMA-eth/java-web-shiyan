<script setup lang="ts">
import { ref } from 'vue';
import { NButton, NInput, NForm, NFormItem, NSwitch } from 'naive-ui';

const username = ref('');
const password = ref('');
const active = ref(false);
const isLogin = ref(true);

const handleLogin = () => {
    console.log('Login', username.value, password.value);
};

const handleRegister = () => {
    console.log('Register', username.value, password.value, active.value);
};
</script>

<template>
    <div class="p-10">
        <div class="container mx-auto text-black flex justify-center p-32 shadow-md bg-[#1a2626] rounded-sm w-full">
            <div class="shadow-sm bg-white p-10 rounded-sm w-[60%]">
                <NForm>
                    <div class="pb-5 flex gap-10">
                        <NButton @click="isLogin = !isLogin" type="primary" class="w-full text-black">
                            Change to {{ isLogin ? 'Register' : 'Login' }}
                        </NButton>
                    </div>
                    <div class="text-center font-bold text-lg p-2">Welcome to {{ isLogin ? 'Login' : 'Register'}}</div>
                    <NFormItem label="User Name">
                        <NInput v-model:value="username" placeholder="Enter User Name" />
                    </NFormItem>
                    <NFormItem label="Password">
                        <NInput v-model:value="password" placeholder="Enter Password" type="password" />
                    </NFormItem>
                    <div v-if="!isLogin" class="pb-5 flex justify-between">
                        <div>Are you Merchant?</div>
                        <NSwitch v-model:value="active" />
                    </div>
                    <NButton @click="isLogin ? handleLogin() : handleRegister()" type="primary" class="text-black w-full">
                            {{ isLogin ? 'Login' : 'Register' }}
                    </NButton>
                </NForm>
            </div>
        </div>
    </div>
</template>

<style scoped>
</style>