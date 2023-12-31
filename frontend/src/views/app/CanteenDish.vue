<script setup lang="ts">
import HeaderVue from '../../components/Header.vue';
import router from '../../router/index';
import { NGrid, NGi, NModal, NCard, NButton, NScrollbar, NRate, NInput } from 'naive-ui';
import { reactive, ref, computed } from 'vue';

const dishData = reactive([
    {
        "dishId": 1,
        "dishName": "Spicy Noodles",
        "price": 13,
        "description": "Noodle",
        "creationTime": "Dec 30, 2023, 2:49:46 PM"
    },
    {
        "dishId": 2,
        "dishName": "Spicy Noodles Updated",
        "price": 13,
        "description": "Noodle Updated",
        "creationTime": "Dec 30, 2023, 2:49:46 PM"
    }
])

const reviewsData = reactive([
    {
        "reviewId": 4,
        "userId": 1,
        "dishId": 3,
        "rating": 5,
        "comment": "Very delicious noodles with perfect spices.",
        "creationTime": "Dec 30, 2023, 3:47:12 PM"
    },
    {
        "reviewId": 4,
        "userId": 1,
        "dishId": 3,
        "rating": 2,
        "comment": "Very delicious noodles with perfect spices.",
        "creationTime": "Dec 30, 2023, 3:47:12 PM"
    },
    {
        "reviewId": 4,
        "userId": 1,
        "dishId": 3,
        "rating": 3,
        "comment": "Very delicious noodles with perfect spices.",
        "creationTime": "Dec 30, 2023, 3:47:12 PM"
    },
    {
        "reviewId": 4,
        "userId": 1,
        "dishId": 3,
        "rating": 3,
        "comment": "Very delicious noodles with perfect spices.",
        "creationTime": "Dec 30, 2023, 3:47:12 PM"
    },
    {
        "reviewId": 4,
        "userId": 1,
        "dishId": 3,
        "rating": 3,
        "comment": "Very delicious noodles with perfect spices.",
        "creationTime": "Dec 30, 2023, 3:47:12 PM"
    }
])

const showRviews = ref(false);
const showCreate = ref(false);
const reviewTitle = ref('Reviews');

const showModal = (text: string) => {
    reviewTitle.value = `Reviews of ${text}`;
    showRviews.value = true;
};

const showCreatePop = () => {
    showCreate.value = !showCreate.value;
}

const commentData = reactive({
    userId: 1,
    dishId: 1,
    rate: 1,
    comment: '',
})

const CreateComment = () => {
    console.log(commentData);
    showCreate.value = false;
}
</script>

<template>
    <HeaderVue />
    <div class="p-10 pt-5 text-white">
        <div class="mx-auto container">
            <NGrid x-gap="12" :cols="4" class="text-black">
                <NGi
                    class="bg-white my-2 p-2 shadow-lg rounded-sm hover:shadow-2xl transition-shadow duration-300"
                    v-for="(item, index) in dishData"
                    :key="index"
                    >
                    <div class="flex flex-col items-start">
                        <h3 class="text-lg font-semibold mb-2">{{ item.dishName }}</h3>
                        <p class="text-gray-600 mb-1">￥{{ item.price }}</p>
                        <p class="text-gray-600 mb-1">{{ item.description }}</p>
                        <p class="text-gray-600">{{ item.creationTime }}</p>
                        <NButton type="primary" class="text-black mt-2 w-full" @click="showModal(item.dishName)">Reviews</NButton>
                    </div>
                </NGi>
            </NGrid>
        </div>
    </div>
    <NModal v-model:show="showRviews">
        <NCard
            style="width: 600px"
            :title="reviewTitle"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
        >
        <div class="flex pb-3 items-center justify-between">
            <div>Create review of {{ reviewTitle.slice(10, -1) }}</div>
            <NButton type="primary" class="text-black" @click="showCreatePop">Create</NButton>
        </div>
        <NScrollbar style="max-height: 400px">
            <div v-for="(item, index) in reviewsData" :key="index" class="border-b-[1px] p-2">
                <div class="flex justify-between items-center">
                    <NRate size="small" readonly :default-value="item.rating" />
                    <div>{{ item.creationTime }}</div>
                </div>
                <div class="pt-2">{{ item.comment }}</div>
            </div>
        </NScrollbar>
        </NCard>
    </NModal>

    <NModal v-model:show="showCreate">
        <NCard
            style="width: 500px"
            title="Create Review"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
        >
            <div class="flex flex-col">
                <div class="flex justify-between items-center">
                    <div>Rating</div>
                    <NRate size="small" v-model:value="commentData.rate" />
                </div>
                <NInput class="my-3" v-model:value="commentData.comment" type="text" placeholder="Comment" />
                <div class="flex justify-between">
                    <NButton type="primary" class="text-black" @click="CreateComment">Create</NButton>
                    <NButton type="primary" class="text-black" @click="showCreatePop">Cancle</NButton>
                </div>
            </div>
        </NCard>
    </NModal>
</template>