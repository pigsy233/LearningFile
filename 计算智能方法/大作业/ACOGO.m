clear;
close all;
clc;
n = 50;%物品数量
V = 1000;%背包最大装载重量
u = [80 82 85 70 72 70 66 50 55 25 50 55 40 48 50 32 22 60 30 32 40 38 35 32 25 28 30 22 25 30 45 30 60 50 20 65 20 25 30 10 20 25 15 10 10 10 4 4 2 1]; %重量
p = [220 208 198 192 180 180 165 162 160 15 8 155 130 125 122 120 118 115 110 105 101 100 100 98 9 6 95 90 88 82 80 77 75 73 72 70 69 66 65 63 60 58 56 5 0 30 20 15 10 8 5 3 1 ];%价值
Alpha = 0.3;
Beta = 0.7;
iter_max = 200;%最大迭代次数
m = 10;%蚂蚁数量
Rho = 0.90;%信息素蒸发速率
Tau = ones(1,n);%信息素矩阵
Table = zeros(m,n);%每一代蚂蚁的背包组成
GO_Table = zeros(5,n);%遗传
Route_best = zeros(iter_max,n);%各代的最优组成
Value_best = zeros(iter_max,1);%各代的最优解
Heu_F = zeros(1,n);%单位重量价值
for i = 1:n
    Heu_F(i) = p(i) / u(i);
end
iter  = 1;
while iter <= iter_max
    
    %生成每只蚂蚁的解
        start = zeros(m,1);
    for i = 1:m
        temp = randperm(n);
        start(i) = temp(1);
    end
    Table(:,1) = start;
    city_index = 1:n;
    for i = 1:m
        for j = 2:n
            if Table(i,j-1) == 0
                continue;
            end
            has_visited = Table(i,1:(j-1));
            allow_index = ~ismember(city_index,has_visited);
            allow = city_index(allow_index);
            temp = 0;
            for l = 1:length(has_visited)
                 temp = temp + u(has_visited(l));
            end
            for l = 1:length(allow)
                if temp + u(allow(l)) > V
                    allow(l) = 0;
                end
            end
            allow(allow == 0) = [];
            if isempty(allow)
                continue;
            end
            P = allow;
            for k = 1:length(allow)
                P(k) = (Tau(allow(k))^Alpha)*(Heu_F(allow(k))^Beta);
            end
            if P == 0
                P = 0;
            else
                P = P / sum(P);
                Pc = cumsum(P);
                target_index = find(Pc >= rand);
                target = allow(target_index(1));
                Table(i,j) = target;
            end
        end
    end
    
    %计算每种组合的价值
    Value = zeros(m,1);
    for i = 1:m
        Route = Table(i,:);
        for j = 1:n
            if Route(j) > 0
                Value(i) = Value(i) + p(Route(j));
            end
        end
    end
    [max_Value,max_index] = max(Value);
    
    %更新最优解
    if iter == 1
        Value_best(iter) = max_Value;
        Route_best(iter,:) = Table(max_index,:);
    else
        if max_Value < Value_best(iter-1)
            Value_best(iter) = Value_best(iter-1);
            Route_best(iter,:) = Route_best(iter-1,:);
        else
            Value_best(iter) = max_Value;
            Route_best(iter,:) = Table(max_index,:);
        end
    end
    iter = iter + 1;
    
    %计算遗传因子
    if iter == 1
        GO_Table(1,:) = Table(max_index,:);
        max1 = GO_Table(1,:);
        max_V = Value(max_index);
        max_I = max_index;
        Value(max_index)=0;
        [max_Value,max_index] = max(Value);
        GO_Table(2,:) = Table(max_index,:);
        max2 = GO_Table(2,:);
        temp = randperm(n);
        GO_Table(3,:) = GO_Table(1,:);
        GO_Table(4,:) = GO_Table(2,:); 
    else
        GO_Table(3,:) = Table(max_index,:);
        max1 = GO_Table(3,:);
        max_V = Value(max_index);
        max_I = max_index;
        Value(max_index)=0;
        [max_Value,max_index] = max(Value);
        GO_Table(4,:) = Table(max_index,:);
        max2 = GO_Table(4,:);
        temp = randperm(n);
    end
    Value(max_I)=max_V;
    if temp(1)>temp(2)
        i = temp(1);
        temp(1) = temp(2);
        temp(2) = i;
    end
    for i = temp(1):temp(2)
        exchange = GO_Table(3,i);
        GO_Table(3,i) = GO_Table(4,i);
        GO_Table(4,i) = exchange;
    end
    temp = randperm(4);
    switch temp(1)
        case 1
            GO_Table(5,:) = GO_Table(1,:);
        case 2
            GO_Table(5,:) = GO_Table(2,:);
        case 3
            GO_Table(5,:) = GO_Table(3,:);
        case 4
            GO_Table(5,:) = GO_Table(4,:);
    end
    for i = 1:5
        temp = randperm(n);
        if GO_Table(i,temp(1)) == 1
            GO_Table(i,temp(1)) = 0;
        else
            GO_Table(i,temp(1)) = 1;
        end
    end
    GO_Weight = zeros(3,1);
    for i = 1:3
        Route = GO_Table(i+2,:);
        for j = 1:n
            if Route(j) > 0
                GO_Weight(i) = GO_Weight(i) + u(Route(j));
            end
        end
    end
    for i = 1:3
        [min_Heu_F,min_Heu_F_Index] =max(Heu_F);
        while GO_Weight(i)>V
            for j = 1:n
                if Route(j) > 0 
                    if Heu_F(j)<min_Heu_F
                        min_Heu_F=Heu_F(j);
                        min_Heu_F_Index = j;
                    end
                end
            end
            GO_Weight(i) = GO_Weight(i) - u(min_Heu_F_Index);
            Route(min_Heu_F_Index) = 0;
        end
        GO_Table(i+2) = Route(i);
    end

    %更新信息素矩阵
    Delta_Tau = zeros(n,n);
    for i = 1:m
        for j = 1:(n-1)
            if Table(i,j) > 0 && Table(i,j+1)>0
                Delta_Tau(Table(i,j),Table(i,j+1)) = Delta_Tau(Table(i,j),Table(i,j+1)) + 1/Value(i);
            end
        end
    end
    Tau = Rho*Tau + Delta_Tau;
    Delta_Tau = zeros(n,n);
    for i = 1:5
        for j = 1:(n-1)
            if GO_Table(i,j) > 0 && GO_Table(i,j+1)>0
                Delta_Tau(GO_Table(i,j),GO_Table(i,j+1)) = Delta_Tau(GO_Table(i,j),GO_Table(i,j+1)) + 1/Value(i);
            end
        end
    end
    Tau = Rho*Tau + Delta_Tau;
    Table = zeros(m,n);
    GO_Table = zeros(5,n);
    GO_Table(1,:) = max1;
    GO_Table(2,:) = max2;
end
plot(Value_best);
title({'最优组合共装下价值为',Value_best(200),'的物品'});
xlabel('迭代次数');
ylabel('最优方案');
