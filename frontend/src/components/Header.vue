<script setup lang="ts">
import router  from "../router/index"

const goto = (path: string) => {
    router.push(path);
};
</script>

<template>
    <nav class="bg-[#1a2626] text-white">
        <div class="container mx-auto px-6 py-3 flex justify-between items-center">
            <div class="flex items-center">
                <span class="font-semibold text-xl tracking-tight">Canteens Hub</span>
            </div>
            <div class="flex items-center space-x-4 font-semibold">
                <div @click="goto('/')" class="hover:text-gray-300">Home</div>
                <div @click="goto('/reviews')" class="hover:text-gray-300">Reviews</div>
                <div @click="goto('/am-canteen')" class="hover:text-gray-300">AM Canteen</div>
                <div @click="goto('/about')" class="hover:text-gray-300">About</div>
            </div>
        </div>
    </nav>
</template>