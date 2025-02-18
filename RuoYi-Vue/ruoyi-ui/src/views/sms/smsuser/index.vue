<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号" prop="account">
        <el-input
          v-model="queryParams.account"
          placeholder="请输入账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
          size="small"
          show-password
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="API密钥" prop="apiKey">
        <el-input
          v-model="queryParams.apiKey"
          placeholder="请输入API密钥"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="短信余额" prop="balance">
        <el-input
          v-model="queryParams.balance"
          placeholder="请输入短信余额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sms:smsuser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sms:smsuser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sms:smsuser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sms:smsuser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="smsuserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="id" />
      <el-table-column label="账号" align="center" prop="account" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="api密钥" align="center" prop="apiKey" />
      <el-table-column label="API安全密钥" align="center" prop="secretKey" />
      <el-table-column label="短信余额" align="center" prop="balance" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sms:smsuser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sms:smsuser:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改短信平台用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号" prop="account">
          <el-input v-model="form.account" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="API密钥" prop="apiKey">
          <el-input v-model="form.apiKey" placeholder="请输入API密钥" />
        </el-form-item>
        <el-form-item label="API安全密钥" prop="secretKey">
          <el-input v-model="form.secretKey" placeholder="请输入API安全密钥" />
        </el-form-item>
        <el-form-item label="短信余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入短信余额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSmsuser, getSmsuser, delSmsuser, addSmsuser, updateSmsuser } from "@/api/sms/smsuser";

export default {
  name: "Smsuser",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 短信平台用户管理表格数据
      smsuserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        account: null,
        password: null,
        apiKey: null,
        balance: null
      },
      // 表单参数
      form: {
        id: null,
        account: null,
        password: null,
        apiKey: null,
        secretKey: null,
        balance: 0
      },
      // 表单校验
      rules: {
        account: [
          { required: true, message: "账号不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        apiKey: [
          { required: true, message: "API密钥不能为空", trigger: "blur" }
        ],
        secretKey: [
          { required: true, message: "API安全密钥不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询短信平台用户管理列表 */
    getList() {
      this.loading = true;
      listSmsuser(this.queryParams).then(response => {
        this.smsuserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        account: null,
        password: null,
        apiKey: null,
        secretKey: null,
        balance: 0
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加短信平台用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSmsuser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改短信平台用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 打印表单数据，看看是否包含 secretKey
          console.log("提交的表单数据:", this.form);
          if (this.form.id != null) {
            updateSmsuser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSmsuser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除短信平台用户管理编号为"' + ids + '"的数据项？').then(function() {
        return delSmsuser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sms/smsuser/export', {
        ...this.queryParams
      }, `smsuser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
