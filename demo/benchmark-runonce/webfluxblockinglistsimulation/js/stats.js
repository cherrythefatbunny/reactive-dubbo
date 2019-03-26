var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "6000",
        "ok": "6000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "211",
        "ok": "211",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "24821",
        "ok": "24821",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "7943",
        "ok": "7943",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2394",
        "ok": "2394",
        "ko": "-"
    },
    "percentiles1": {
        "total": "8872",
        "ok": "8872",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9069",
        "ok": "9069",
        "ko": "-"
    },
    "percentiles3": {
        "total": "9299",
        "ok": "9299",
        "ko": "-"
    },
    "percentiles4": {
        "total": "13543",
        "ok": "13543",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 99,
        "percentage": 2
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 42,
        "percentage": 1
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 5859,
        "percentage": 98
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.692",
        "ok": "18.692",
        "ko": "-"
    }
},
contents: {
"req_blocking-list-0fb11": {
        type: "REQUEST",
        name: "blocking_list",
path: "blocking_list",
pathFormatted: "req_blocking-list-0fb11",
stats: {
    "name": "blocking_list",
    "numberOfRequests": {
        "total": "6000",
        "ok": "6000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "211",
        "ok": "211",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "24821",
        "ok": "24821",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "7943",
        "ok": "7943",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2394",
        "ok": "2394",
        "ko": "-"
    },
    "percentiles1": {
        "total": "8872",
        "ok": "8872",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9069",
        "ok": "9069",
        "ko": "-"
    },
    "percentiles3": {
        "total": "9299",
        "ok": "9299",
        "ko": "-"
    },
    "percentiles4": {
        "total": "13543",
        "ok": "13543",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 99,
        "percentage": 2
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 42,
        "percentage": 1
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 5859,
        "percentage": 98
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.692",
        "ok": "18.692",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
