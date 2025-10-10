<template>
  <div class="p-6">
    <!-- Filters -->
    <el-card class="mb-4">
      <el-form :inline="true" :model="filters" @submit.prevent>
        <el-form-item label="Title">
          <el-input v-model="filters.title" placeholder="Book title" clearable @keyup.enter="handleSearch" />
        </el-form-item>
        <el-form-item label="Author">
          <el-input v-model="filters.author" placeholder="Author" clearable @keyup.enter="handleSearch" />
        </el-form-item>
        <el-form-item label="Category">
          <!-- Fill your categories here -->
          <el-select v-model="filters.category" placeholder="Select category" clearable filterable>
            <el-option
              v-for="c in categories"
              :key="c.value"
              :label="c.label"
              :value="c.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="In Stock">
          <el-select v-model="filters.inStock" placeholder="All" clearable>
            <el-option :value="true" label="Yes" />
            <el-option :value="false" label="No" />
          </el-select>
        </el-form-item>
        <el-form-item label="Order">
          <el-radio-group v-model="filters.order">
            <el-radio-button label="desc">Desc</el-radio-button>
            <el-radio-button label="asc">Asc</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">Search</el-button>
          <el-button @click="handleReset">Reset</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- Table -->
    <el-card>
      <el-table :data="rows" v-loading="loading" border style="width: 100%">
        <el-table-column prop="title" label="Title" min-width="220" />
        <el-table-column prop="author" label="Author" min-width="160" />
        <el-table-column prop="category" label="Category" min-width="140" />
        <el-table-column prop="inStock" label="In Stock" width="110">
          <template #default="{ row }">
            <el-tag :type="row.inStock ? 'success' : 'danger'">{{ row.inStock ? 'Yes' : 'No' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="updatedAt" label="Updated" min-width="160">
          <template #default="{ row }">{{ formatDate(row.updatedAt) }}</template>
        </el-table-column>
      </el-table>

      <div class="flex justify-end mt-4">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          :page-sizes="[5, 10, 20, 50]"
          v-model:page-size="pager.pageSize"
          v-model:current-page="pager.page"
          @current-change="handlePageChange"
          @size-change="handleSizeChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref, watch, onMounted } from 'vue'

// ============================
// Filters & Pagination state
// ============================
const filters = reactive({
  title: '',
  author: '',
  category: '', // You can keep as string or array for multi-select
  inStock: undefined, // true | false | undefined (all)
  order: 'desc', // 'asc' | 'desc'
})

const pager = reactive({
  page: 1,
  pageSize: 10,
})

const loading = ref(false)
const rows = ref([])
const total = ref(0)

// ============================
// Category options (replace with your own)
// ============================
const categories = ref([
  { label: 'Fiction', value: 'fiction' },
  { label: 'Non-fiction', value: 'nonfiction' },
  { label: 'Science', value: 'science' },
  { label: 'History', value: 'history' },
])

// ============================
// Data fetching
// Replace mockFetchBooks with your real API request.
// ============================
async function fetchBooks() {
  loading.value = true
  try {
    const { list, total: t } = await mockFetchBooks({
      ...filters,
      page: pager.page,
      pageSize: pager.pageSize,
    })
    rows.value = list
    total.value = t
  } finally {
    loading.value = false
  }
}

function handleSearch() {
  pager.page = 1
  fetchBooks()
}
function handleReset() {
  filters.title = ''
  filters.author = ''
  filters.category = ''
  filters.inStock = undefined
  filters.order = 'desc'
  pager.page = 1
  pager.pageSize = 10
  fetchBooks()
}
function handlePageChange() {
  fetchBooks()
}
function handleSizeChange() {
  pager.page = 1
  fetchBooks()
}

onMounted(fetchBooks)

// Optional: auto re-fetch when filters change (debounced)
let timer
watch(filters, () => {
  clearTimeout(timer)
  timer = setTimeout(() => {
    pager.page = 1
    fetchBooks()
  }, 400)
})

// ============================
// Utilities
// ============================
function formatDate(iso) {
  const d = new Date(iso)
  const y = d.getFullYear()
  const m = String(d.getMonth() + 1).padStart(2, '0')
  const day = String(d.getDate()).padStart(2, '0')
  const hh = String(d.getHours()).padStart(2, '0')
  const mm = String(d.getMinutes()).padStart(2, '0')
  return `${y}-${m}-${day} ${hh}:${mm}`
}

// ============================
// MOCK API (for demo only)
// Replace with your backend call (e.g., axios.get('/api/books', { params: {...} }))
// ============================
function makeMockData(n = 120) {
  const cats = categories.value.map(c => c.value)
  const authors = ['Alice', 'Bob', 'Carol', 'David', 'Eve', 'Frank']
  const list = Array.from({ length: n }).map((_, i) => {
    const price = (Math.random() * 50 + 5)
    return {
      id: i + 1,
      title: `Book ${i + 1}`,
      author: authors[i % authors.length],
      category: cats[i % cats.length],
      inStock: Math.random() > 0.3,
      price,
      updatedAt: new Date(Date.now() - Math.random() * 1000 * 3600 * 24 * 60).toISOString(),
    }
  })
  return list
}

const MOCK_POOL = makeMockData()

async function mockFetchBooks(params) {
  // Simulate network latency
  await new Promise(r => setTimeout(r, 250))

  const { title, author, category, inStock, order, page, pageSize } = params

  let data = [...MOCK_POOL]

  if (title) {
    const t = title.toLowerCase()
    data = data.filter(x => x.title.toLowerCase().includes(t))
  }
  if (author) {
    const a = author.toLowerCase()
    data = data.filter(x => x.author.toLowerCase().includes(a))
  }
  if (category) {
    data = data.filter(x => x.category === category)
  }
  if (typeof inStock === 'boolean') {
    data = data.filter(x => x.inStock === inStock)
  }

  // Sort by updatedAt for demo
  data.sort((a, b) => {
    const diff = new Date(a.updatedAt) - new Date(b.updatedAt)
    return order === 'asc' ? diff : -diff
  })

  const total = data.length
  const start = (page - 1) * pageSize
  const end = start + pageSize
  const list = data.slice(start, end)

  return { list, total }
}
</script>

<style scoped>
.p-6 { padding: 24px; }
.mb-4 { margin-bottom: 16px; }
.flex { display: flex; }
.justify-end { justify-content: flex-end; }
.mt-4 { margin-top: 16px; }
</style>
