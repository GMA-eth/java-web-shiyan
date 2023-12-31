<script setup lang="ts">
import { ref, reactive } from 'vue';
import { NButton, NModal, NCard, NScrollbar, NInput } from 'naive-ui';
import HeaderVue from '../../components/Header.vue';
import router from '../../router/index';

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

const showUpdate = ref(false);
const showCreate = ref(false);
const showDelete = ref(false);
const canteenId = ref(false);
const reviewTitle = ref('Update');
const updateData = ref({})

const showUpdatePop = (item: any) => {
    updateData.value = item;
    showUpdate.value = true;
}

const showCreatePop = () => {
    showCreate.value = !showCreate.value;
}

const showDeletePop = (id: number) => {
    canteenId.value = id;
    showDelete.value = !showDelete.value;
}

const createData = reactive({
    canteenName:"",
    location:"",
    businessHours:"",
    description:"",
})

const goto = (path: string) => {
    router.push(`/canteen/${path}`);
};
</script>

<template>
    <HeaderVue />
    <div class="p-10">
        <div class="container mx-auto text-black flex justify-center p-2 flex-col shadow-md bg-white rounded-sm w-full">
            <div class="flex justify-between p-2">
                <div class="text-3xl font-semibold">Canteen Management</div>
                <NButton type="primary" class="text-black" @click="showCreatePop">Create</NButton>
            </div>
            <NScrollbar style="max-height: 400px">
                <table class="table-auto border-collapse border border-slate-400 w-full text-sm">
                    <thead>
                        <tr class="bg-gray-200">
                            <th class="border border-slate-300 p-2">ID</th>
                            <th class="border border-slate-300 p-2">Name</th>
                            <th class="border border-slate-300 p-2">Location</th>
                            <th class="border border-slate-300 p-2">Business Hours</th>
                            <th class="border border-slate-300 p-2">Description</th>
                            <th class="border border-slate-300 p-2">Creation Time</th>
                            <th class="border border-slate-300 p-2">Operation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="canteen in canteens" :key="canteen.canteenId" class="hover:bg-gray-100">
                            <td class="border border-slate-300 p-2">{{ canteen.canteenId }}</td>
                            <td class="border border-slate-300 p-2">{{ canteen.canteenName }}</td>
                            <td class="border border-slate-300 p-2">{{ canteen.location }}</td>
                            <td class="border border-slate-300 p-2">{{ canteen.businessHours }}</td>
                            <td class="border border-slate-300 p-2">{{ canteen.description }}</td>
                            <td class="border border-slate-300 p-2">{{ canteen.creationTime }}</td>
                            <td class="border border-slate-300 p-2 flex gap-5">
                                <NButton type="primary" class="text-black" @click="showUpdatePop(canteen)">Update</NButton>
                                <NButton type="primary" class="text-black" @click="showDeletePop(canteen.canteenId)">Delete</NButton>
                                <NButton type="primary" class="text-black" @click="goto(canteen.canteenId)">View</NButton>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </NScrollbar>
        </div>
    </div>

    <NModal v-model:show="showUpdate"> 
        <NCard
            style="width: 600px"
            :title="`${reviewTitle} ${updateData.canteenName}`"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
        >
        <NScrollbar style="max-height: 300px">
            <div class="my-3">
                <NInput class="my-3" v-model:value="updateData.canteenName" type="text" placeholder="canteen Name" />
                <NInput class="my-3" v-model:value="updateData.location" type="text" placeholder="Location" />
                <NInput class="my-3" v-model:value="updateData.businessHours" type="text" placeholder="Business Hours" />
                <NInput class="my-3" v-model:value="updateData.description" type="text" placeholder="Description" />
            </div>
            <NButton type="primary" class="text-black">Update</NButton>
        </NScrollbar>
        </NCard>
    </NModal>

    <NModal v-model:show="showDelete"> 
        <NCard
            style="width: 400px"
            title="Delete Canteen"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
        >
        <dir>
            <div class="text-lg font-semibold">Are you sure to delete this canteen?</div>
            <div class="flex justify-end gap-5 pt-2">
                <NButton type="primary" class="text-black">Yes</NButton>
                <NButton type="primary" class="text-black" @click="showDeletePop(1)">No</NButton>
            </div>
        </dir>
        </NCard>
    </NModal>
    
    <NModal v-model:show="showCreate"> 
        <NCard
            style="width: 600px"
            title="Create Canteen"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
        >
        <NScrollbar style="max-height: 300px">
            <div class="my-3">
                <NInput class="my-3" v-model:value="createData.canteenName" type="text" placeholder="canteen Name" />
                <NInput class="my-3" v-model:value="createData.location" type="text" placeholder="Location" />
                <NInput class="my-3" v-model:value="createData.businessHours" type="text" placeholder="Business Hours example: 07:00 - 20:00" />
                <NInput class="my-3" v-model:value="createData.description" type="text" placeholder="Description" />
            </div>
            <div class="flex justify-between items-center">
                <NButton type="primary" class="text-black">Create</NButton>
                <NButton type="primary" class="text-black" @click="showCreatePop">Cancle</NButton>
            </div>
        </NScrollbar>
        </NCard>
    </NModal>
</template>