<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="手机号码" prop="recipient">
        <el-input v-model="queryParams.recipient" placeholder="请输入手机号码" clearable style="width: 200px"/>
      </el-form-item>
      <el-form-item label="发送账号" prop="account">
        <el-select v-model="queryParams.account" placeholder="请选择发送账号" clearable style="width: 200px">
          <el-option
            v-for="user in userOptions"
            :key="user.account"
            :label="user.account"
            :value="user.account"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="发送状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择发送状态" clearable style="width: 200px">
          <el-option label="成功" value="1" />
          <el-option label="失败" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="发送时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="smsList">
      <el-table-column label="日志编号" align="center" prop="id" />
      <el-table-column label="发送账号" align="center" prop="account" />
      <el-table-column label="手机号码" align="center" prop="recipient" width="120" />
      <el-table-column label="短信内容" align="center" prop="content" :show-overflow-tooltip="true" />
      <el-table-column label="发送状态" align="center" prop="status">
        <template slot-scope="scope">
          <span>{{ scope.row.status === '1' ? '成功' : '失败' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发送时间" align="center" prop="sendTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['sms:log:query']"
          >详情</el-button>
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

    <!-- 查看短信发送记录详细 -->
    <el-dialog title="短信详情" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="发送账号">
          <span>{{ form.account }}</span>
        </el-form-item>
        <el-form-item label="手机号码">
          <span>{{ form.recipient }}</span>
        </el-form-item>
        <el-form-item label="短信内容">
          <span>{{ form.content }}</span>
        </el-form-item>
        <el-form-item label="发送状态">
          <span>{{ form.status === '1' ? '成功' : '失败' }}</span>
        </el-form-item>
        <el-form-item label="发送时间">
          <span>{{ parseTime(form.sendTime) }}</span>
        </el-form-item>
        <el-form-item label="备注" v-if="form.remark">
          <span>{{ form.remark }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSmsLog, getLog } from "@/api/sms/log";
import { listSmsuser } from "@/api/sms/smsuser";

export default {
  name: "SmsLog",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 短信发送记录表格数据
      smsList: [],
      // 用户列表选项
      userOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recipient: null,
        account: null,
        status: null,
        params: {}
      },
      // 表单参数
      form: {}
    };
  },
  created() {
    this.getList();
    this.getUserOptions();
  },
  methods: {
    /** 查询短信日志列表 */
    getList() {
      this.loading = true;
      if (this.dateRange && this.dateRange.length > 0) {
        this.queryParams.params = {
          beginTime: this.dateRange[0],
          endTime: this.dateRange[1]
        };
      }
      listSmsLog(this.queryParams).then(response => {
        this.smsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取用户列表 */
    getUserOptions() {
      listSmsuser().then(response => {
        if (response.code === 200) {
          this.userOptions = response.rows;
          console.log('用户列表:', this.userOptions);
        }
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 查看按钮操作 */
    handleView(row) {
      getLog(row.id).then(response => {
        this.form = response.data;
        this.open = true;
      });
    }
  }
};
</script> 