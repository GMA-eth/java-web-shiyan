<script setup lang="ts">
import { ref } from 'vue';
import HeaderVue from '../components/Header.vue';
import { NGrid, NGi, NButton } from 'naive-ui';
import router from '../router/index';

const canteens = ref([
    {
        "canteenId": 1,
        "canteenName": "USST Canteen",
        "location": "jun gong road 516",
        "businessHours": "08:00-09:00",
        "description": "Usst Canteen is the best",
        "creationTime": "Dec 30, 2023, 3:11:23 AM"
    },
    {
        "canteenId": 2,
        "canteenName": "USST Canteen Updated",
        "location": "jun gong road 516 Updated",
        "businessHours": "08:00-09:00 Updated",
        "description": "Usst Canteen is the best Updated",
        "creationTime": "Dec 30, 2023, 12:21:36 PM"
    },
    {
        "canteenId": 2,
        "canteenName": "USST Canteen Updated",
        "location": "jun gong road 516 Updated",
        "businessHours": "08:00-09:00 Updated",
        "description": "Usst Canteen is the best Updated",
        "creationTime": "Dec 30, 2023, 12:21:36 PM"
    }
]);

const goto = (path: string) => {
    router.push(`/canteen/${path}`);
};
</script>

<template>
    <HeaderVue />
    <div class="p-10 pt-5">
        <div class="container mx-auto text-black w-full h-full">
            <NGrid x-gap="12" :cols="3" class="text-black">
                <NGi v-for="(item, index) in canteens" :key="index" class="bg-white rounded-t-sm p-4"> 
                    <div class="flex flex-col bg-white">
                        <div class="text-lg font-semibold">
                            {{ item.canteenName }}
                        </div>
                        <div class="flex justify-between pt-3 items-center">
                            <div class="flex flex-col gap-1">
                                <div>{{ item.location }}</div>
                                <div>{{ item.businessHours }}</div>
                                <div>{{ item.description }}</div>
                                <div>{{ item.creationTime }}</div>
                            </div>
                            <div>
                                <NButton type="primary" class="text-black" @click="goto(item.canteenId)">View</NButton>
                            </div>
                        </div>
                    </div>
                </NGi>
            </NGrid>
        </div>
    </div>
</template>

<style scoped>
</style>
